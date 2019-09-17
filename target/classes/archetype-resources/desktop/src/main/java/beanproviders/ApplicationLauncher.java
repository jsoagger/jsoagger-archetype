#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*-
 * ========================LICENSE_START=================================
 * JSoagger 
 * %%
 * Copyright (C) 2019 JSOAGGER
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

package ${package}.beanproviders;

import ${package}.beanproviders.desktop.DesktopApplicationLauncher;
import ${package}.beanproviders.mobile.MobileApplicationLauncher;

/**
 * @author Ramilafananana  VONJISOA
 */
public class ApplicationLauncher {

  /**
   * @param args Application arguments
   */
  public static void main(String[] args) {
    boolean withPreloader = Boolean.valueOf(getArgs("--jsoagger.preloader", args, "false"));
    String mode = getArgs("--jsoagger.client.mode", args, "desktop");

    if (withPreloader) {
      if("desktop".equalsIgnoreCase(mode)) {
        System.setProperty("javafx.preloader", "io.github.jsoagger.jfxcore.preloader.desktop.EPDesktopPreloader");
      }
    }

    if(mode == null || "desktop".equalsIgnoreCase(mode)) {
      DesktopApplicationLauncher.launch(DesktopApplicationLauncher.class, args);
    }
    else {
      MobileApplicationLauncher.launch(MobileApplicationLauncher.class, args);
    }
  }

  /**
   * @param key
   * @param args
   * @param defaultValue
   * @return
   */
  public static String getArgs(String key, String[] args, String defaultValue) {
    for (String arg : args) {
      String[] splitted = arg.split("=");
      if(splitted.length == 2) {
        if (splitted[0].equals(key)) {
          return splitted[1];
        }
      }
    }

    return defaultValue;
  }
}

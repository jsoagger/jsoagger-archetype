#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 *
 */
package ${package}.beanproviders.mobile;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import io.github.jsoagger.core.i18n.MessageSource;
import io.github.jsoagger.core.ioc.api.BeanFactory;
import io.github.jsoagger.core.ioc.api.annotations.Bean;
import io.github.jsoagger.core.ioc.api.annotations.BeansProvider;
import io.github.jsoagger.core.ioc.api.annotations.Named;
import io.github.jsoagger.core.ioc.api.annotations.Singleton;
import io.github.jsoagger.core.ioc.api.annotations.View;
import io.github.jsoagger.jfxcore.api.IModelProvider;
import io.github.jsoagger.jfxcore.api.css.IStylesheetManager;
import io.github.jsoagger.jfxcore.api.services.ApplicationContextService;
import io.github.jsoagger.jfxcore.api.services.CommonComponentsServices;
import io.github.jsoagger.jfxcore.api.services.GlobalComponentsService;
import io.github.jsoagger.jfxcore.api.services.Services;
import io.github.jsoagger.jfxcore.api.services.ViewConfigurationService;
import io.github.jsoagger.jfxcore.engine.components.css.StyleSheetsManager;
import io.github.jsoagger.jfxcore.engine.controller.main.StandardController;
import io.github.jsoagger.jfxcore.engine.controller.main.layout.BottomTabPaneViewStructure;
import io.github.jsoagger.jfxcore.engine.providers.integration.CommonCompsServices;
import io.github.jsoagger.jfxcore.engine.providers.integration.JSoaggerFXApplicationContextService;
import io.github.jsoagger.jfxcore.engine.providers.integration.JSonViewConfigurationService;
import io.github.jsoagger.jfxcore.engine.providers.integration.JsonGlobalCompsService;

/**
 * @author Ramilafananana VONJISOA
 *
 */
@BeansProvider
public class MobileViewStructureBeansConfiguration {

  @Bean
  @Singleton
  @Named("platformViewStructure")
  public BottomTabPaneViewStructure platformViewStructure() {
    BottomTabPaneViewStructure structure = new BottomTabPaneViewStructure();
    structure.setScreenProperties(screenProperties());
    structure.setStyleSheetManager(styleSheetManager());
    structure.setPlatformProperties(platformProperties());
    return  structure;
  }


  @Bean
  @Singleton
  @Named("platformProperties")
  public Properties platformProperties() {
    Properties platformProperties = new Properties();
    platformProperties.setProperty("tempFolderPath", "temp");
    platformProperties.setProperty("applicationDataFolderPath", "temp");
    platformProperties.setProperty("localNotificationsFolderPath", "temp");
    platformProperties.setProperty("applicationWindowName", "Mobile application");
    platformProperties.setProperty("applicationTitle", "Mobile application");
    platformProperties.setProperty("fullScreen", "false");
    platformProperties.setProperty("platformRootStructureId", "MobileLayoutRSView");
    platformProperties.setProperty("loginRootStructureId", "LoginRootStructure");
    platformProperties.setProperty("platformType", "SIMUL_MOBILE");
    platformProperties.setProperty("applicationViewConfigMode", "offline");
    platformProperties.setProperty("applicationConnexionMode", "allow_anonymous");
    platformProperties.setProperty("full.screen.support", "false");
    return platformProperties;
  }

  @Bean
  @Singleton
  @Named("styleSheetManager")
  public IStylesheetManager styleSheetManager() {
    StyleSheetsManager sheetsManager = new StyleSheetsManager();
    sheetsManager.getStyleSheetsPath().add("classpath:/css/color/accent/blue.css");
    sheetsManager.getStyleSheetsPath().add("classpath:/css/color/primary/green.css");
    sheetsManager.getStyleSheetsPath().add("classpath:/css/content/light/light-mobile.css");
    sheetsManager.getStyleSheetsPath().add("classpath:/css/undecorator/undecorator.css");
    sheetsManager.getStyleSheetsPath().add("classpath:/io/github/jsoagger/core/jfx/controller/login/login.css");
    return sheetsManager;
  }

  @Bean
  @Singleton
  @Named("customStyleSheetsPath")
  public List<String> customStyleSheetsPath(){
    List<String> d = new ArrayList<>();
    d.add("classpath:/css/desktop-override.css");
    return d;
  }

  @Bean
  @Singleton
  @Named("wizardProperties")
  public Properties providesWizardProperties() {
    Properties wizardProperties = new Properties();
    wizardProperties.setProperty("width", "300");
    wizardProperties.setProperty("height", "400");
    return wizardProperties;
  }

  @Bean
  @Singleton
  @Named("screenProperties")
  public Properties screenProperties() {
    Properties screenProperties = new Properties();
    screenProperties.setProperty("width", "340");
    screenProperties.setProperty("height", "600");
    screenProperties.setProperty("minWidth", "340");
    screenProperties.setProperty("minHeight", "600");
    screenProperties.setProperty("maxWidth", "340");
    screenProperties.setProperty("maxHeight", "600");
    return screenProperties;
  }


  @Bean
  @Singleton
  @Named("ApplicationContextService")
  public ApplicationContextService appContextService() {
    JSoaggerFXApplicationContextService mobile = new JSoaggerFXApplicationContextService();
    return mobile;
  }


  @Bean
  @Singleton
  @Named("ViewConfigurationService")
  public ViewConfigurationService viewConfigurationService() {
    JSonViewConfigurationService viewConfigurationService = new JSonViewConfigurationService();
    return viewConfigurationService;
  }

  @Bean
  @Singleton
  @Named("GlobalComponentsService")
  public GlobalComponentsService jsonGlobalCompsService() {
    JsonGlobalCompsService jsonGlobalComps = new JsonGlobalCompsService();
    jsonGlobalComps.getFiles().add("/io/github/jsoagger/jfxcore/demoapp/common/CoreActions.json");
    jsonGlobalComps.getFiles().add("/io/github/jsoagger/jfxcore/demoapp/common/CoreActionsModel.json");
    jsonGlobalComps.getFiles().add("/io/github/jsoagger/jfxcore/demoapp/common/CoreAttributes.json");
    jsonGlobalComps.getFiles().add("/io/github/jsoagger/jfxcore/demoapp/common/CoreComponents.json");

    jsonGlobalComps.getFiles().add("/io/github/jsoagger/jfxcore/demoapp/common/common-components.json");
    jsonGlobalComps.getFiles().add("/io/github/jsoagger/jfxcore/demoapp/common/element-components.json");
    jsonGlobalComps.getFiles().add("/io/github/jsoagger/jfxcore/demoapp/common/search-components.json");
    jsonGlobalComps.loadFiles();
    return jsonGlobalComps;
  }

  @Bean
  @Singleton
  @Named("CommonCompsServices")
  public CommonCompsServices commonCompsServices() {
    CommonCompsServices commonCompsServices = new CommonCompsServices();
    return commonCompsServices;
  }

  @Bean
  @Singleton
  @Named("IntegrationService")
  public Services integrationService() {
    Services services = Services.instance();
    services.setAppContextService((ApplicationContextService) BeanFactory.instance().getBean("ApplicationContextService"));
    services.setCommonComponentsServices((CommonComponentsServices) BeanFactory.instance().getBean("CommonCompsServices"));
    services.setGlobalConfigService((GlobalComponentsService) BeanFactory.instance().getBean("GlobalComponentsService"));
    services.setViewConfigurationService((ViewConfigurationService) BeanFactory.instance().getBean("ViewConfigurationService"));
    return services;
  }


  @Bean
  @Named("GetStartedMessageSource")
  public MessageSource GetStartedMessageSource() {
    MessageSource messageSource = new MessageSource();
    messageSource.setUseCodeAsDefaultMessage(true);
    messageSource.setDefaultEncoding("UTF-8");
    messageSource.getBasenames().add("rootstructure.message");
    messageSource.setParentMessageSource((MessageSource) Services.getBean("CoreGeneralMessageSource"));
    return messageSource;
  }

  @Bean
  @Named("GetStartedWelcomeView")
  @View(locations= {"/rootstructure/GetStartedWelcomeView.xml"})
  public StandardController GetStartedWelcomeView() {
    StandardController c = new StandardController();
    c.setModelProvider((IModelProvider) BeanFactory.instance().getBean("RootStructureModelLoader"));
    c.setMessageSource((MessageSource) Services.getBean("GetStartedMessageSource"));
    c.addViewDefinition("/rootstructure/GetStartedWelcomeView.json");
    return c;
  }
}

#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 *
 */
package ${package}.beanproviders.mobile;

import java.util.ArrayList;
import java.util.List;

import io.github.jsoagger.core.i18n.MessageSource;
import io.github.jsoagger.core.ioc.api.annotations.Bean;
import io.github.jsoagger.core.ioc.api.annotations.BeansProvider;
import io.github.jsoagger.core.ioc.api.annotations.Named;
import io.github.jsoagger.core.ioc.api.annotations.View;
import io.github.jsoagger.jfxcore.api.IModelProvider;
import io.github.jsoagger.jfxcore.api.IParentResponsiveMatrix;
import io.github.jsoagger.jfxcore.api.MenuConfigurationProvider;
import io.github.jsoagger.jfxcore.api.services.Services;
import io.github.jsoagger.jfxcore.api.view.IViewLayoutManager;
import io.github.jsoagger.jfxcore.engine.components.menu.PrimaryMenuProvider;
import io.github.jsoagger.jfxcore.engine.controller.PrimaryMenuController;
import io.github.jsoagger.jfxcore.engine.controller.main.DoubleHeaderRootStructureController;
import io.github.jsoagger.jfxcore.engine.controller.main.StandardTabPaneController;
import io.github.jsoagger.jfxcore.engine.controller.roostructure.content.RootStructureContentController;
import io.github.jsoagger.jfxcore.engine.controller.roostructure.header.ToolbarController;
import io.github.jsoagger.jfxcore.engine.controller.roostructure.util.ParentResponsiveMatrix;

/**
 * @author Ramilafananana VONJISOA
 *
 */
@BeansProvider
public class MobileAppViewsConfiguration {

  @Bean
  @Named("MobilePrimaryHeaderToolbarView")
  @View(locations = {"/mobile/PrimaryHeaderToolbarView.xml", "/io/github/jsoagger/jfxcore/engine/controller/HeaderToolbar.xml"},
  outputFilePath = "/mobile/", outputFileName ="PrimaryHeaderToolbarView")
  public ToolbarController MobilePrimaryHeaderToolbarView() {
    ToolbarController tbc = new ToolbarController();
    tbc.setMessageSource((MessageSource) Services.getBean("GetStartedMessageSource"));
    tbc.setResponsiveMatrix((IParentResponsiveMatrix) Services.getBean("MobilePrimaryHeaderToolbarResponsiveMatrix"));
    tbc.addViewDefinition("/mobile/PrimaryHeaderToolbarView.json");
    return tbc;
  }


  @Bean
  @Named("MobilePrimaryHeaderToolbarResponsiveMatrix")
  public ParentResponsiveMatrix MobilePrimaryHeaderToolbarResponsiveMatrix() {
    List<String> m = MobilePrimaryHeaderToolbarResponsiveMatrixDefinition();
    ParentResponsiveMatrix p = new ParentResponsiveMatrix(m);
    return p;
  }

  @Bean
  @Named("MobilePrimaryHeaderToolbarResponsiveMatrixDefinition")
  public List<String> MobilePrimaryHeaderToolbarResponsiveMatrixDefinition(){
    List<String> s = new ArrayList<>();
    s.add("0${symbol_pound}0.15:0.0:0.85${symbol_pound}:hide:");
    return s;
  }


  @Bean
  @Named("MobileLayoutRSContentView")
  @View(locations = "/mobile/MobileLayoutRSContentView.xml")
  public RootStructureContentController MobileLayoutRSContentView() {
    RootStructureContentController tbc = new RootStructureContentController();
    tbc.setMessageSource((MessageSource) Services.getBean("GetStartedMessageSource"));
    tbc.addViewDefinition("/mobile/MobileLayoutRSContentView.json");
    return tbc;
  }


  @Bean
  @Named("MobileLayoutRSView")
  @View(locations = "/mobile/MobileLayoutRSView.xml")
  public DoubleHeaderRootStructureController MobileLayoutRSView() {
    DoubleHeaderRootStructureController tbc = new DoubleHeaderRootStructureController();
    tbc.setMessageSource((MessageSource) Services.getBean("GetStartedMessageSource"));
    tbc.setModelProvider((IModelProvider) Services.getBean("RootStructureModelLoader"));
    tbc.addViewDefinition("/mobile/MobileLayoutRSView.json");
    return tbc;
  }


  @Bean
  @Named("MobileRootTabPaneView")
  @View(locations = "/mobile/MobileRootTabPaneView.xml")
  public StandardTabPaneController MobileRootTabPaneView() {
    StandardTabPaneController tbc = new StandardTabPaneController();
    tbc.setMessageSource((MessageSource) Services.getBean("GetStartedMessageSource"));
    tbc.setModelProvider((IModelProvider) Services.getBean("RootStructureModelLoader"));
    tbc.setLayoutManager((IViewLayoutManager) Services.getBean("FullTabPaneLayoutManager"));
    tbc.addViewDefinition("/mobile/MobileRootTabPaneView.json");
    return tbc;
  }


  @Bean
  @Named("MobilePrimaryMenuView")
  @View(locations= {"/mobile/MobilePrimaryMenuView.xml"})
  public PrimaryMenuController MobilePrimaryMenuView() {
    PrimaryMenuController tbc = new PrimaryMenuController();
    tbc.setMessageSource((MessageSource) Services.getBean("GetStartedMessageSource"));
    tbc.setMenuProvider((MenuConfigurationProvider) Services.getBean("MobilePrimaryMenuProvider"));
    tbc.addViewDefinition("/mobile/MobilePrimaryMenuView.json");
    return tbc;
  }


  @Bean
  @Named("MobilePrimaryMenuProvider")
  @View(locations = "/mobile/MobilePrimaryMenuDefinition.xml")
  public PrimaryMenuProvider MobilePrimaryMenuProvider() {
    PrimaryMenuProvider tbc = new PrimaryMenuProvider();
    tbc.setPrimaryMenu("/mobile/MobilePrimaryMenuDefinition.json");
    return tbc;
  }
}

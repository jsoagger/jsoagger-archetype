#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 *
 */
package ${package}.beanproviders.desktop;

import java.util.ArrayList;
import java.util.List;

import io.github.jsoagger.core.i18n.MessageSource;
import io.github.jsoagger.core.ioc.api.BeanFactory;
import io.github.jsoagger.core.ioc.api.annotations.Bean;
import io.github.jsoagger.core.ioc.api.annotations.BeansProvider;
import io.github.jsoagger.core.ioc.api.annotations.Named;
import io.github.jsoagger.core.ioc.api.annotations.Singleton;
import io.github.jsoagger.core.ioc.api.annotations.View;
import io.github.jsoagger.jfxcore.api.IModelProvider;
import io.github.jsoagger.jfxcore.api.MenuConfigurationProvider;
import io.github.jsoagger.jfxcore.api.services.Services;
import io.github.jsoagger.jfxcore.api.view.IViewLayoutManager;
import io.github.jsoagger.jfxcore.engine.components.menu.PrimaryMenuProvider;
import io.github.jsoagger.jfxcore.engine.controller.HeaderLessTwoPanesViewController;
import io.github.jsoagger.jfxcore.engine.controller.PrimaryMenuController;
import io.github.jsoagger.jfxcore.engine.controller.SecondaryMenuController;
import io.github.jsoagger.jfxcore.engine.controller.main.DoubleHeaderRootStructureController;
import io.github.jsoagger.jfxcore.engine.controller.main.RootStructureController;
import io.github.jsoagger.jfxcore.engine.controller.main.StandardController;
import io.github.jsoagger.jfxcore.engine.controller.roostructure.content.RootStructureContentController;
import io.github.jsoagger.jfxcore.engine.controller.roostructure.header.ToolbarController;
import io.github.jsoagger.jfxcore.engine.controller.roostructure.util.ParentResponsiveMatrix;

/**
 * @author Ramilafananana VONJISOA
 *
 */
@BeansProvider
public class DesktopAppViewsConfiguration {


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

  @Bean
  @Named("GetStartedRSView")
  @View(locations= {"/rootstructure/GetStartedRSView.xml"})
  public RootStructureController demoDashboardRSView() {
    DoubleHeaderRootStructureController mobileLayoutRSView = new DoubleHeaderRootStructureController();
    mobileLayoutRSView.setModelProvider((IModelProvider) BeanFactory.instance().getBean("RootStructureModelLoader"));
    mobileLayoutRSView.setMessageSource((MessageSource) Services.getBean("GetStartedMessageSource"));
    mobileLayoutRSView.addViewDefinition("/rootstructure/GetStartedRSView.json");
    return mobileLayoutRSView;
  }

  @Bean
  @Named("HeaderLessGetStartedRSView")
  @View(locations= {"/rootstructure/HeaderLessGetStartedRSView.xml"})
  public RootStructureController GetHeaderLessStartedRSView() {
    DoubleHeaderRootStructureController mobileLayoutRSView = new DoubleHeaderRootStructureController();
    mobileLayoutRSView.setModelProvider((IModelProvider) BeanFactory.instance().getBean("RootStructureModelLoader"));
    mobileLayoutRSView.setMessageSource((MessageSource) Services.getBean("GetStartedMessageSource"));
    mobileLayoutRSView.addViewDefinition("/rootstructure/HeaderLessGetStartedRSView.json");
    return mobileLayoutRSView;
  }


  @Bean
  @Named("GetStartedRSContentView")
  @View(locations= {"/rootstructure/GetStartedRSContentView.xml"})
  public RootStructureContentController DemoDashboardRSContentView() {
    RootStructureContentController rscc = new RootStructureContentController();
    rscc.setModelProvider((IModelProvider) BeanFactory.instance().getBean("RootStructureModelLoader"));
    rscc.addViewDefinition("/rootstructure/GetStartedRSContentView.json");
    return rscc;
  }

  @Bean
  @Named("HeaderLessGetStartedRSContentView")
  @View(locations= {"/rootstructure/HeaderLessGetStartedRSContentView.xml"})
  public RootStructureContentController GetStartedHeaderLessRSContentView() {
    RootStructureContentController rscc = new RootStructureContentController();
    rscc.setModelProvider((IModelProvider) BeanFactory.instance().getBean("RootStructureModelLoader"));
    rscc.addViewDefinition("/rootstructure/HeaderLessGetStartedRSContentView.json");
    return rscc;
  }


  @Bean
  @Named("HeaderLessContentView")
  @View(locations= {"/rootstructure/HeaderLessContentView.xml"}, outputFileName ="HeaderLessContentView")
  public HeaderLessTwoPanesViewController HeaderLessGetStartedContentView() {
    HeaderLessTwoPanesViewController mobileLayoutRSView = new HeaderLessTwoPanesViewController();
    mobileLayoutRSView.setModelProvider((IModelProvider) BeanFactory.instance().getBean("RootStructureModelLoader"));
    mobileLayoutRSView.setMessageSource((MessageSource) Services.getBean("GetStartedMessageSource"));
    mobileLayoutRSView.setLayoutManager((IViewLayoutManager) Services.getBean("TwoHPanesWithLeftMenuLayoutManager"));
    mobileLayoutRSView.addViewDefinition("/rootstructure/HeaderLessContentView.json");
    return mobileLayoutRSView;
  }


  @Bean
  @Named("PrimaryHeaderToolbarView")
  @View(locations= {"/rootstructure/PrimaryHeaderToolbarView.xml",
  "/io/github/jsoagger/jfxcore/engine/controller/HeaderToolbar.xml"})
  public ToolbarController primaryHeaderToolbarView() {
    ToolbarController tpv = new ToolbarController();
    tpv.setMessageSource((MessageSource) Services.getBean("GetStartedMessageSource"));
    tpv.setResponsiveMatrix(GetStartedPrimaryHeaderToolbarResponsiveMatrix());
    tpv.setModelProvider((IModelProvider) BeanFactory.instance().getBean("RootStructureModelLoader"));
    tpv.addViewDefinition("/rootstructure/PrimaryHeaderToolbarView.json");
    return tpv;
  }


  @Bean
  @Named("PrimaryMenuView")
  @View(locations= {"/rootstructure/PrimaryMenuView.xml"})
  public PrimaryMenuController mobilePrimaryMenuView() {
    PrimaryMenuController pmc = new PrimaryMenuController();
    pmc.setMessageSource((MessageSource) Services.getBean("GetStartedMessageSource"));
    pmc.setMenuProvider((MenuConfigurationProvider) Services.getBean("PrimaryMenuProvider"));
    pmc.addViewDefinition("/rootstructure/PrimaryMenuView.json");
    return pmc;
  }


  @Bean
  @Named("HeaderLessLeftMenuViewProvider")
  @View(locations = {"/rootstructure/HeaderLessLeftMenuView.xml"})
  public PrimaryMenuProvider HeaderLessLeftMenuViewProvider() {
    PrimaryMenuProvider menuProvider = new PrimaryMenuProvider();
    menuProvider.setPrimaryMenu("/rootstructure/HeaderLessLeftMenuView.json");
    return menuProvider;
  }


  @Bean
  @Named("HeaderLessLeftMenuView")
  public SecondaryMenuController HeaderLessLeftMenuView() {
    SecondaryMenuController sc = new SecondaryMenuController();
    sc.setModelProvider((IModelProvider) BeanFactory.instance().getBean("RootStructureModelLoader"));
    sc.setMessageSource((MessageSource) Services.getBean("GetStartedMessageSource"));
    sc.setMenuProvider((MenuConfigurationProvider) Services.getBean("HeaderLessLeftMenuViewProvider"));
    return sc;
  }


  @Bean
  @Named("PrimaryMenuProvider")
  @View(locations= {"/rootstructure/PrimaryMenuDefinition.xml"})
  public PrimaryMenuProvider primaryMenuProvider() {
    PrimaryMenuProvider pmc = new PrimaryMenuProvider();
    pmc.setPrimaryMenu("/rootstructure/PrimaryMenuDefinition.json");
    return pmc;
  }

  @Bean
  @Named("GetStartedPrimaryHeaderToolbarResponsiveMatrix")
  public ParentResponsiveMatrix GetStartedPrimaryHeaderToolbarResponsiveMatrix() {
    List<String> m = PrimaryHeaderToolbarResponsiveMatrixDefinition();
    ParentResponsiveMatrix p = new ParentResponsiveMatrix(m);
    return p;
  }

  @Bean
  @Singleton
  @Named("PrimaryHeaderToolbarResponsiveMatrixDefinition")
  public List<String> PrimaryHeaderToolbarResponsiveMatrixDefinition() {
    List<String> m = new ArrayList<>();
    m.add("0${symbol_pound}0.3:0:0.7${symbol_pound}:hide:");
    return m;
  }
}

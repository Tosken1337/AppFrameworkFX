package com.github.tosken1337.afwfx;

import com.github.tosken1337.afwfx.core.DefaultViewStateController;
import com.github.tosken1337.afwfx.core.ViewStateController;
import com.github.tosken1337.afwfx.util.Resources;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Module;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import de.saxsys.mvvmfx.MvvmFX;
import de.saxsys.mvvmfx.guice.MvvmfxGuiceApplication;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Sebastian Greif on 07.08.2017.
 * Copyright di support 2017
 */
public class Application extends MvvmfxGuiceApplication {
    private static final Logger log = LogManager.getLogger(Application.class);

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("default", Locale.getDefault());

    private final DefaultViewStateController viewStateController = new DefaultViewStateController();

    @Inject
    private NotificationCenter notificationCenter;

    @Override
    public void startMvvmfx(final Stage stage) throws Exception {
        log.info("Starting application");
        MvvmFX.setGlobalResourceBundle(resourceBundle);

        viewStateController.setStage(stage);
        viewStateController.loadFromClassPath(Application.class);

        //stage.getIcons().setAll(new Image(AppStarter.class.getResource("/icon.png").toExternalForm()));
        stage.setTitle(resourceBundle.getString("window.title"));
        stage.setResizable(true);
        //stage.initStyle(StageStyle.TRANSPARENT);
        stage.setOnCloseRequest(event -> notificationCenter.publish(ApplicationEvent.Exit.getId()));


        // Load and add all css files from classpath resource folder 'css'
        Resources.getResourcesFromClasspath("./css", ".css").forEach(s -> viewStateController.addStylesheet("/css/" + s));


        viewStateController.showDefaultView();
        log.info("Showing default view");
    }

    @Override
    public void initGuiceModules(final List<Module> modules) throws Exception {
        super.initGuiceModules(modules);
        modules.add(new AbstractModule() {
            @Override
            protected void configure() {
                bind(ViewStateController.class).toInstance(viewStateController);
                bind(Config.class).toInstance(ConfigFactory.load());
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}

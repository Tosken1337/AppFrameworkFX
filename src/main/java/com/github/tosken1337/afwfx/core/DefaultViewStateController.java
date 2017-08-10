package com.github.tosken1337.afwfx.core;

import com.github.tosken1337.afwfx.annotation.AppView;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sebastian Greif on 07.08.2017.
 * Copyright di support 2017
 */
public class DefaultViewStateController implements ViewStateController {

    private Map<String, Pair<ApplicationView, Parent>> views = new HashMap<>();
    private Scene scene;
    private Stage stage;
    private String defaultView;
    private String currentView;

    @SuppressWarnings("unchecked")
    public void loadFromClassPath(final Class clazz) {
        final Reflections reflections = new Reflections(clazz.getPackage().getName());
        String defaultViewName = null;
        for (final Class<?> viewClass : reflections.getTypesAnnotatedWith(AppView.class)) {
            if (ApplicationView.class.isAssignableFrom(viewClass)) {
                Class<ApplicationView> cls = (Class<ApplicationView>) viewClass;
                ViewTuple<? extends ApplicationView, ? extends ViewModel> viewTuple = FluentViewLoader.fxmlView(cls).load();

                AppView appView = cls.getAnnotation(AppView.class);
                views.put(appView.viewStateName(), new Pair<>(viewTuple.getCodeBehind(), viewTuple.getView()));
                if (appView.defaultView()) {
                    defaultViewName = appView.viewStateName();
                }
            }
        }

        defaultView = defaultViewName;
        scene = new Scene(views.get(defaultView).getValue());
        stage.setScene(scene);
    }

    @Override
    public void showDefaultView() {
        switchView(defaultView);
        stage.show();
    }

    @Override
    public void switchView(final String viewStateName) {
        if (!views.containsKey(viewStateName)) {
            return;
        }

        if (currentView != null) {
            views.get(currentView).getKey().onViewGetsHidden();
        }

        final Pair<ApplicationView, Parent> viewPair = views.get(viewStateName);
        viewPair.getKey().onViewGetsVisible();
        scene.setRoot(viewPair.getValue());
        currentView = viewStateName;
    }

    public void setStage(final Stage stage) {
        this.stage = stage;
    }
}

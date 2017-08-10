package com.github.tosken1337.afwfx.example.view;

import com.github.tosken1337.afwfx.annotation.AppView;
import com.github.tosken1337.afwfx.core.ApplicationView;
import com.github.tosken1337.afwfx.core.ViewStateController;
import com.github.tosken1337.afwfx.example.viewmodel.MainViewModel;
import com.google.inject.Inject;
import com.typesafe.config.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Sebastian Greif on 07.08.2017.
 * Copyright di support 2017
 */
@AppView(viewStateName = "main", defaultView = true)
public class MainView implements ApplicationView<MainViewModel>, Initializable {

    @Inject
    private ViewStateController viewStateController;

    @Inject
    private Config config;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

    }

    @FXML
    public void switchView(ActionEvent actionEvent) {
        viewStateController.switchView("detail");
    }

    @Override
    public void onViewGetsVisible() {

    }

    @Override
    public void onViewGetsHidden() {

    }
}

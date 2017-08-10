package com.github.tosken1337.afwfx.example.view;

import com.github.tosken1337.afwfx.annotation.AppView;
import com.github.tosken1337.afwfx.core.ApplicationView;
import com.github.tosken1337.afwfx.core.ViewStateController;
import com.github.tosken1337.afwfx.example.viewmodel.MainViewModel;
import com.github.tosken1337.afwfx.example.viewmodel.SecondViewModel;
import com.google.inject.Inject;
import com.typesafe.config.Config;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Sebastian Greif on 07.08.2017.
 * Copyright di support 2017
 */
@AppView(viewStateName = "detail")
public class SecondView implements ApplicationView<SecondViewModel>, Initializable {

    @FXML
    private Label configLabel;

    @Inject
    private ViewStateController viewStateController;

    @Inject
    private Config config;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        String string = config.getString("secondView.message");
        configLabel.setText(string);
    }

    @Override
    public void onViewGetsVisible() {

    }

    @Override
    public void onViewGetsHidden() {

    }
}

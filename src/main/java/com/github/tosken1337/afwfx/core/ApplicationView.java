package com.github.tosken1337.afwfx.core;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.ViewModel;

/**
 * Created by Sebastian Greif on 07.08.2017.
 * Copyright di support 2017
 */
public interface ApplicationView<ViewModelType extends ViewModel> extends FxmlView<ViewModelType> {
    void onViewGetsVisible();
    void onViewGetsHidden();
}

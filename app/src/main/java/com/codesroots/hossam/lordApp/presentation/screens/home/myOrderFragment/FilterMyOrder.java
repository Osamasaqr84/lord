package com.codesroots.hossam.lordApp.presentation.screens.home.myOrderFragment;

import com.codesroots.hossam.lordApp.entities.MYOrdersModel;

import java.util.List;

public class FilterMyOrder {

    List<MYOrdersModel.DataBean> commpleteOrderData;
    List<MYOrdersModel.DataBean> notCommpleteOrderData;

    public FilterMyOrder(List<MYOrdersModel.DataBean> commpleteOrderData, List<MYOrdersModel.DataBean> notCommpleteOrderData) {
        this.commpleteOrderData = commpleteOrderData;
        this.notCommpleteOrderData = notCommpleteOrderData;
    }
}

package com.istad.ecommerce.data.api.request;

import com.google.gson.annotations.SerializedName;

public class UpdateRequest {

    @SerializedName("data")
    private UpdateRequestData updateRequestData;

    public UpdateRequestData getUpdateRequestData() {
        return updateRequestData;
    }

    public void setUpdateRequestData(UpdateRequestData updateRequestData) {
        this.updateRequestData = updateRequestData;
    }

    @Override
    public String toString() {
        return "UpdateRequest{" +
                "updateRequestData=" + updateRequestData +
                '}';
    }
}

package com.example.TheNews.model;

import com.example.TheNews.entity.LikeEntity;

public class Like {
    private long like_id;
    public static Like toModel(LikeEntity entity) {
        Like model = new Like();
        model.setLike_id(entity.getLike_id());
        return model;
    }

    public Like() {
    }

    public long getLike_id() {
        return like_id;
    }

    public void setLike_id(long like_id) {
        this.like_id = like_id;
    }
}

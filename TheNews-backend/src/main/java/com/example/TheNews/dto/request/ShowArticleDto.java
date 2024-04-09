package com.example.TheNews.dto.request;
import lombok.*;

import java.sql.Timestamp;

@Data
public class ShowArticleDto {
    private java.sql.Timestamp publicationDate;

    public Timestamp getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Timestamp publicationDate) {
        this.publicationDate = publicationDate;
    }
}

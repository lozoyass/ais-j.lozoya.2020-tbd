package es.codeurjc.ais.book;

import java.util.ArrayList;
import java.util.List;

import es.codeurjc.ais.review.Review;

public class BookDetail extends Book{

    private final int MAX_BOOK_DESCRIPTION_LENGTH = 950;

    private String description;

    private String imageUrl;

    private String[] subjects;

    private List<Review> reviews = new ArrayList<>();

    public String getDescription() {
        return checkBookDescriptionLength();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "BookDetail [description=" + checkBookDescriptionLength() + ", imageUrl=" + imageUrl + "]";
    }

    public List<Review> getReviews() {
        return reviews;
    }
    
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String[] getSubjects() {
        return subjects;
    }

    public void setSubjects(String[] subjects) {
        this.subjects = subjects;
    }

    private String checkBookDescriptionLength() {
        if (description.length() > MAX_BOOK_DESCRIPTION_LENGTH) {
            String descriptionShorted = description.substring(0, MAX_BOOK_DESCRIPTION_LENGTH-1);
            descriptionShorted += "...";
            return descriptionShorted;
        }
        else {
            return description;
        }
    }
}

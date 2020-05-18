package pl.somday.java14.book.usersbooks;

import pl.somday.java14.user.AppUser;

import javax.persistence.*;

@Entity(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @Column(name = "description")
    private String description;

    @Column(name = "categories")
    private String categories;

    @Column(name = "title")
    private String title;

    @Column(name = "subtitle")
    private String subtitle;

    @Column(name = "authors")
    private String authors;

    @Column(name = "page_count")
    private Integer pageCount;

    @Column(name = "buy_link")
    private String buyLink;

    @Column(name = "image_link")
    private String imageLink;

    protected Book(){
    }

    protected Book(final Long id, final AppUser user, final String description, final String categories,
                final String title, final String subtitle, final String authors,
                final Integer pageCount, final String buyLink, final String imageLink) {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(final AppUser user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(final String categories) {
        this.categories = categories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(final String subtitle) {
        this.subtitle = subtitle;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(final String authors) {
        this.authors = authors;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(final Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getBuyLink() {
        return buyLink;
    }

    public void setBuyLink(final String buyLink) {
        this.buyLink = buyLink;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(final String imageLink) {
        this.imageLink = imageLink;
    }

    public static BookBuilder builder() {
        return new BookBuilder();
    }

    public static class BookBuilder {
        private Long id;
        private AppUser user;
        private String description;
        private String categories;
        private String title;
        private String subtitle;
        private String authors;
        private Integer pageCount;
        private String buyLink;
        private String imageLink;

        BookBuilder() {
        }

        public BookBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BookBuilder user(AppUser user) {
            this.user = user;
            return this;
        }

        public BookBuilder description(String description) {
            this.description = description;
            return this;
        }

        public BookBuilder categories(String categories) {
            this.categories = categories;
            return this;
        }

        public BookBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder subtitle(String subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        public BookBuilder authors(String authors) {
            this.authors = authors;
            return this;
        }

        public BookBuilder pageCount(Integer pageCount) {
            this.pageCount = pageCount;
            return this;
        }

        public BookBuilder buyLink(String buyLink) {
            this.buyLink = buyLink;
            return this;
        }

        public BookBuilder imageLink(String imageLink) {
            this.imageLink = imageLink;
            return this;
        }

        public Book build() {
            return new Book(id, user, description, categories, title, subtitle, authors, pageCount, buyLink, imageLink);
        }

        public String toString() {
            return "Book.BookBuilder(id=" + this.id + ", user=" + this.user + ", description=" + this.description + ", categories=" + this.categories + ", title=" + this.title + ", subtitle=" + this.subtitle + ", authors=" + this.authors + ", pageCount=" + this.pageCount + ", buyLink=" + this.buyLink + ", imageLink=" + this.imageLink + ")";
        }
    }

}

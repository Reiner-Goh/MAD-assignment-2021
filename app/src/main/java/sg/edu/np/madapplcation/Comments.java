package sg.edu.np.madapplcation;

public class Comments {

    private String userid;
    private String comment;


    public Comments(String userid, String comment) {
        this.userid = userid;
        this.comment = comment;


    }

    public Comments() {
    }

    public String getUserid(){return userid;}

    public void setUserid(String userid){this.userid = userid;}

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /*public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }*/

}

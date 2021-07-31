package sg.edu.np.madapplcation;

public class Comments {

    private String comment;
    private String username;
    private String commentid;

    public Comments(String comment, String username, String commentid) {
        this.comment = comment;
        this.username = username;
        this.commentid = commentid;
    }

    public Comments() {
    }

    public String getComment() {
            return comment;
        }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCommentid() {
        return commentid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid;
    }

}

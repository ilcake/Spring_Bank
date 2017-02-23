package global.sesoc.sebank.vo;

public class Reply {
	private int replynum;
	private int boardnum;
	private String custid;
	private String text;
	private String inputdate;

	public Reply() {
	}

	public Reply(int replynum, int boardnum, String custid, String text, String inputdate) {
		super();
		this.replynum = replynum;
		this.boardnum = boardnum;
		this.custid = custid;
		this.text = text;
		this.inputdate = inputdate;
	}

	public int getReplynum() {
		return replynum;
	}

	public void setReplynum(int replynum) {
		this.replynum = replynum;
	}

	public int getBoardnum() {
		return boardnum;
	}

	public void setBoardnum(int boardnum) {
		this.boardnum = boardnum;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getInputdate() {
		return inputdate;
	}

	public void setInputdate(String inputdate) {
		this.inputdate = inputdate;
	}

	@Override
	public String toString() {
		return "Reply [replynum=" + replynum + ", boardnum=" + boardnum + ", custid=" + custid + ", text=" + text
				+ ", inputdate=" + inputdate + "]";
	}

}

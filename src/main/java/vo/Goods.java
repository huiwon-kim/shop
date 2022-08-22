package vo;

public class Goods {
	
	private GoodsImg goodsImg; // 이러면 굿즈가 굿즈img도 받을 수 있엉
//	// 이게 가장 FM인데 이러려면 정규화가 잘되어있어야한대
	
	
	
	private int goodsNo;
	private String goodsName;
	private int goodsPrice;
	private String updateDate;
	private String createDate;
	private String soldOut;
	
	
	public int getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(int goodsNo) {
		this.goodsNo = goodsNo;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getSoldOut() {
		return soldOut;
	}
	public void setSoldOut(String soldOut) {
		this.soldOut = soldOut;
	}
	
}


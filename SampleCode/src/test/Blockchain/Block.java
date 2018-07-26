package test.Blockchain;

import java.util.Arrays;

public class Block {

	private int previousHash;
	private String data;
	private int hash;

	public Block(String data, int previousHash) {
		this.setData(data);
		this.setPreviousHash(previousHash);
		// Mix the content of this block with previous hash to create the hash of this
		// new block
		// and that's what makes it block chain
		this.setHash(Arrays.hashCode(new Integer[] { data.hashCode(), previousHash }));
	}

	public int getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(int previousHash) {
		this.previousHash = previousHash;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getHash() {
		return hash;
	}

	public void setHash(int hash) {
		this.hash = hash;
	}
}

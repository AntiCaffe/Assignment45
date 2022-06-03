package org.dfpl.lecture.database.assignment4.assignment18011656;

import java.util.List;

@SuppressWarnings("unused")
public class ThreeWayBPlusTreeNode {

	// Data Abstraction은 예시일 뿐 자유롭게 B+ Tree의 범주 안에서 어느정도 수정가능
	private ThreeWayBPlusTreeNode parent;
	private List<Integer> keyList;
	private List<ThreeWayBPlusTreeNode> children;


	public ThreeWayBPlusTreeNode(ThreeWayBPlusTreeNode parent,int in, List<ThreeWayBPlusTreeNode> children) {
		this.parent = parent;
		this.keyList.add(in);
		this.children = children;
	} //생성자 지정

	public ThreeWayBPlusTreeNode getParent() {
		return parent;
	}

	public void setParent(ThreeWayBPlusTreeNode parent) {
		this.parent = parent;
	}

	public List<Integer> getKeyList() {
		return keyList;
	}

	public void setKeyList(List<Integer> keyList) {
		this.keyList = keyList;
	}

	public List<ThreeWayBPlusTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<ThreeWayBPlusTreeNode> children) {
		this.children = children;
	}

}


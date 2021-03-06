package org.dfpl.lecture.database.assignment4.assignment18011656;

import java.util.*;

@SuppressWarnings("unused")
public class ThreeWayBPlusTreeNode {

	// Data Abstraction은 예시일 뿐 자유롭게 B+ Tree의 범주 안에서 어느정도 수정가능
	private ThreeWayBPlusTreeNode parent;
	private List<Integer> keyList;
	private List<ThreeWayBPlusTreeNode> children;


	public ThreeWayBPlusTreeNode(ThreeWayBPlusTreeNode parent,int in, ThreeWayBPlusTreeNode e) {
			this.parent = parent;
			this.keyList = new ArrayList<>();
			this.children = new ArrayList<>();
			this.keyList.add(in);
			if(e != null) {
				this.children.add(e);
			}
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


	public List<ThreeWayBPlusTreeNode> getChildren() {
		return children;
	}


}


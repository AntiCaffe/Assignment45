package org.dfpl.lecture.database.assignment4.assignment18011656;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NavigableSet;
import java.util.SortedSet;

@SuppressWarnings("unused")
public class ThreeWayBPlusTree implements NavigableSet<Integer> {

	// Data Abstraction은 예시일 뿐 자유롭게 B+ Tree의 범주 안에서 어느정도 수정가능
	private ThreeWayBPlusTreeNode root;
	private LinkedList<ThreeWayBPlusTreeNode> leafList;

	/**
	 * 과제 Assignment4를 위한 메소드:
	 * 
	 * key로 검색하면 root부터 시작하여, key를 포함할 수 있는 leaf node를 찾고 key가 실제로 존재하면 해당 Node를
	 * 반환하고, 그렇지 않다면 null을 반환한다. 중간과정을 System.out.println(String) 으로 출력해야 함. 3 way
	 * B+ tree에서 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17 이 순서대로
	 * add되었다고 했을 때,
	 * 
	 * 예: getNode(15)을 수행하였을 때 
	 * > start finding 15
	 * > larger than or equal to 9
	 * > larger than or equal to 13
	 * > larger than or equal to 15
	 * > less than 16
	 * > 15 found 
	 * 위의 6 문장을
	 * 콘솔에 출력하고 15를 포함한 ThreeWayBPlusTreeNode를 반환함
	 * 
	 * @param key
	 * @return
	 */
	public ThreeWayBPlusTreeNode getNode(Integer key) {
		System.out.println("start finding"+key);
		ThreeWayBPlusTreeNode search = root;
		while(search.getChildren().size() != 0) {
			if (search.getKeyList().get(0) > key) {
				System.out.println("less than " + search.getKeyList().get(0));
				search = search.getChildren().get(0);
			}
			else {
				System.out.println("larger than or equal to" + search.getKeyList().get(0));
				search = search.getChildren().get(1);
			}
		}


		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 과제 Assignment4를 위한 메소드: 
	 * 
	 * inorder traversal을 수행하여, 값을 오름차순으로 출력한다. 
	 * 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17 이 순서대로
	 * add되었다고 했을 때,
	 * 1
	 * 2
	 * 3
	 * 4
	 * 5
	 * 6
	 * 7
	 * 8
	 * 9
	 * 10
	 * 11
	 * 12
	 * 13
	 * 14
	 * 15
	 * 16
	 * 17
	 * 위와 같이 출력되어야 함. 
	 * Note: Bottom의 LinkedList 순회를 하면 안됨
	 */
	public void inorderTraverse() {
		for(int i = 0; i<leafList.size(); i++)
		{
			System.out.println(leafList.get(i).getKeyList());
		}
		// TODO Auto-generated method stub
	}

	@Override
	public Comparator<? super Integer> comparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer first() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer last() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Integer e) { //리프노드서 부터 올리는 bottom-up 방식으로 구현했습니다.
		if(leafList.size() == 0){
			ThreeWayBPlusTreeNode newnode = new ThreeWayBPlusTreeNode(null,e,null);
			leafList.add(newnode);
			root = leafList.getLast();
		} //맨 처음 노드 추가

		//root가 null 아닐때
		else {
			leafList.getLast().getKeyList().add(e);
			//leaf노드 분할 할때
			if(leafList.getLast().getKeyList().size() > 2){
				int input = leafList.getLast().getKeyList().remove(1); //분할점
				int input2 = leafList.getLast().getKeyList().remove(1); //분할점 뒤의 값

				ThreeWayBPlusTreeNode newleaf = new ThreeWayBPlusTreeNode(leafList.getLast().getParent(),input,null); //새 리프 노드 & 최근 노드 부모와 동일하게 설정
				newleaf.getKeyList().add(input2);
				leafList.addLast(newleaf); //리프 리스트에 새 리프 노드 연결


				if(leafList.getLast().getParent() == null) { // 부모 노드 없을때

					ThreeWayBPlusTreeNode newparent = new ThreeWayBPlusTreeNode(null,input,leafList.get(leafList.size()-1));//새 부모 노드
					newparent.getChildren().add(leafList.get(leafList.size()-2)); //새 부모노드에 기존 차일드 등록
					newparent.getChildren().add(leafList.getLast()); //새 부모노드에 차일드 등록

					leafList.getLast().setParent(newparent); //새 리프노드에 부모 등록
					leafList.get(leafList.size()-1).setParent(newparent);//기존 리프노드에 부모 등록

					root = newparent;
				}

				//부모 노드 있을때
				else{
					leafList.getLast().getParent().getKeyList().add(input);
					leafList.getLast().getParent().getChildren().add(leafList.getLast());
				}
			}

			ThreeWayBPlusTreeNode parent = leafList.getLast().getParent(); //마지막 리프노드 부모 받아오기

			while(parent != null){
				if(parent.getKeyList().size() > 2) { //부모 노드 분할 필요할때
					int right = parent.getKeyList().remove(parent.getKeyList().size()-1); //오른쪽 노드 값
					ThreeWayBPlusTreeNode rightnode = new ThreeWayBPlusTreeNode(null,right,null);
					if(parent == root) //최상위 노드라면
					{
						int mid = parent.getKeyList().remove(parent.getKeyList().size()-1); // 가운데 노드 값
						ThreeWayBPlusTreeNode middle = new ThreeWayBPlusTreeNode(null,mid,parent);

						middle.getChildren().add(rightnode); //가운데 노드 오른쪽 연결
						rightnode.setParent(middle); // 오른쪽 노드 가운데 연결
						parent.setParent(middle); //기존 왼쪽노드 가운데 연결

						root = middle;
					}
					else { //최상위 노드가 아니라면
						parent.getParent().getKeyList().add(parent.getKeyList().remove(parent.getKeyList().size()-1));
						rightnode.setParent(parent.getParent());
						parent.getParent().getChildren().add(rightnode);
					}
					//왼쪽 노드에서 2개 끌고옴
					rightnode.getChildren().add(parent.getChildren().remove(parent.getChildren().size()-2));
					rightnode.getChildren().add(parent.getChildren().remove(parent.getChildren().size()-1));
				}

				else { //상위 노드로 이동
					parent = parent.getParent();
				}
			}



		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Integer> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer lower(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer floor(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer ceiling(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer higher(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer pollFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer pollLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<Integer> descendingSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Integer> descendingIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<Integer> subSet(Integer fromElement, boolean fromInclusive, Integer toElement,
			boolean toInclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<Integer> headSet(Integer toElement, boolean inclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<Integer> tailSet(Integer fromElement, boolean inclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Integer> subSet(Integer fromElement, Integer toElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Integer> headSet(Integer toElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Integer> tailSet(Integer fromElement) {
		// TODO Auto-generated method stub
		return null;
	}

	//생성자
	public ThreeWayBPlusTree() {
		this.root = null;
		this.leafList = new LinkedList<>();
	}
}

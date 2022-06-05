package org.dfpl.lecture.database.assignment4.assignment18011656;

import java.util.*;

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
		System.out.println("start finding " + key); //탐색 시작
		ThreeWayBPlusTreeNode search = root; //루트노드 받아옴
		while (search.getChildren().size() > 0) //더해야할 리프노드 찾기
		{
			if (search.getKeyList().size() == 2) { //노드 아이템 개수 2개인 경우
				if (search.getKeyList().get(0) > key) //왼쪽
					search = search.getChildren().get(0);
				else if (search.getKeyList().get(1) <= key) //오른쪽
					search = search.getChildren().get(2);
				else //가운데
					search = search.getChildren().get(1);

			} else if (search.getKeyList().size() == 1) { //노드 아이템 개수 1개
				if (search.getKeyList().get(0) > key) { //왼쪽
					System.out.println("less than "+search.getKeyList().get(0));
					search = search.getChildren().get(0);
				}
				else { //오른쪽
					System.out.println("larger than or equal to "+search.getKeyList().get(0));
					search = search.getChildren().get(1);
				}
			}
		}

			for (int i = 0; i < search.getKeyList().size(); i++) //리프노드에서 탐색 시작
			{
				if (search.getKeyList().get(i) == key) { //리프노드의 키리스트 전부 확인
					System.out.println(key + " found"); //찾으면 출력과 함께 함수 종료
					return null;
				}
			}
			System.out.println(key + " not found"); //못찾으면 출력 후 함수 종료
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
		ThreeWayBPlusTreeNode search = root;
		System.out.println(leafList.getFirst().getKeyList().get(0)); //처음 노드 값
		inorderrecurs(search); //재귀 함수를 통한 트리 순회
		System.out.println(leafList.getLast().getKeyList().get(1)); //맨 뒤 노드 값


		// TODO Auto-generated method stub
	}
	public void inorderrecurs(ThreeWayBPlusTreeNode t)
	{
		if(t.getChildren().size() > 0)
		{
			inorderrecurs(t.getChildren().get(0));
			System.out.println(t.getKeyList().get(0));
			inorderrecurs(t.getChildren().get(1));
		}
	}

	@Override
	public Comparator<? super Integer> comparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer first() {
		// TODO Auto-generated method stub
		return leafList.getFirst().getKeyList().get(leafList.getFirst().getKeyList().size()-1);
	}

	@Override
	public Integer last() {
		// TODO Auto-generated method stub
		return leafList.getLast().getKeyList().get(leafList.getLast().getKeyList().size()-1);
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
	public boolean add(Integer e) {
		if(leafList.size() == 0){
			ThreeWayBPlusTreeNode newnode = new ThreeWayBPlusTreeNode(null,e,null);
			leafList.add(newnode);
			root = leafList.getLast();
		} //맨 처음 노드 추가
		else {
			ThreeWayBPlusTreeNode search = root;

			while(search.getChildren().size() >0) //더해야할 리프노드 찾기
			{
				if(search.getKeyList().size() ==2)
				{
					if(search.getKeyList().get(0) > e)
						search = search.getChildren().get(0);
					else if(search.getKeyList().get(1) <= e)
						search = search.getChildren().get(2);
					else
						search = search.getChildren().get(1);

				}
				else if(search.getKeyList().size() == 1)
				{
					if(search.getKeyList().get(0) > e)
						search = search.getChildren().get(0);
					else
						search = search.getChildren().get(1);
				}
			}

//			System.out.println(e);
//			if(search.getParent() != null)
//				System.out.println(search.getParent().getKeyList()+"더해야할곳 부모");


			search.getKeyList().add(e); // 찾아서 더해주기
			search.getKeyList().sort(Integer::compareTo); // 정렬

			//leaf노드 분할 할때
			if(search.getKeyList().size() > 2){
				int input = search.getKeyList().remove(1); //분할점
				int input2 = search.getKeyList().remove(1); //분할점 뒤의 값

				ThreeWayBPlusTreeNode newleaf = new ThreeWayBPlusTreeNode(search.getParent(),input,null); //새 리프 노드 & 최근 노드 부모와 동일하게 설정
				newleaf.getKeyList().add(input2);
				newleaf.getKeyList().sort(Integer::compareTo);
				leafList.add(leafList.indexOf(search)+1,newleaf); //리프 리스트에 새 리프 노드 연결


				if(search.getParent() == null) { // 부모 노드 없을때

					ThreeWayBPlusTreeNode newparent = new ThreeWayBPlusTreeNode(null,input,null);//새 부모 노드
					newparent.getChildren().add(leafList.getFirst()); //새 부모노드에 기존 차일드 등록
					newparent.getChildren().add(leafList.getLast()); //새 부모노드에 새 차일드 등록

					leafList.getLast().setParent(newparent); //새 리프노드에 부모 등록
					leafList.get(leafList.size()-2).setParent(newparent);//기존 리프노드에 부모 등록

					root = newparent; //새 루트노드
				}

				//부모 노드 있을때
				else{
					search.getParent().getKeyList().add(input);
					search.getParent().getKeyList().sort(Integer::compareTo);
					search.getParent().getChildren().add(search.getParent().getChildren().indexOf(search)+1,newleaf);
				}
			}

			ThreeWayBPlusTreeNode parent = search.getParent();//수정했던 노드의 부모 노드 받아오기
//			if(parent != null)
//				System.out.println(parent.getKeyList());



			while(parent != null){
				if(parent.getKeyList().size() > 2) { //부모 노드 분할 필요할때
					int right = parent.getKeyList().remove(parent.getKeyList().size()-1); //오른쪽 노드 값
					int mid = parent.getKeyList().remove(parent.getKeyList().size()-1); // 가운데 노드 값
					ThreeWayBPlusTreeNode rightnode = new ThreeWayBPlusTreeNode(null,right,null);

					//왼쪽 노드에서 2개 끌고옴
					rightnode.getChildren().add(parent.getChildren().remove(parent.getChildren().size()-2));
					rightnode.getChildren().get(rightnode.getChildren().size()-1).setParent(rightnode);
					rightnode.getChildren().add(parent.getChildren().remove(parent.getChildren().size()-1));
					rightnode.getChildren().get(rightnode.getChildren().size()-1).setParent(rightnode);

					if(parent == root) //최상위 노드라면
					{
						ThreeWayBPlusTreeNode middle = new ThreeWayBPlusTreeNode(null,mid,parent);

						middle.getChildren().add(rightnode); //가운데 노드 오른쪽 연결
						rightnode.setParent(middle); // 오른쪽 노드 가운데 연결
						parent.setParent(middle); //기존 왼쪽노드 가운데 연결

						root = middle;
					}
					else { //최상위 노드가 아니라면
						parent.getParent().getKeyList().add(mid);
						parent.getParent().getKeyList().sort(Integer::compareTo);

						rightnode.setParent(parent.getParent());
						parent.getParent().getChildren().add(parent.getParent().getChildren().indexOf(parent)+1,rightnode);

					}


				}

				else { //상위 노드로 이동
					parent = parent.getParent();
				}
			}
//			for(ThreeWayBPlusTreeNode a : leafList)
//			{
//				System.out.print(a.getKeyList());
//				if(a.getParent()!=null) {
//					System.out.println();
//					System.out.println(a.getParent().getKeyList() + "부모노드");
//				}
//			}
//
//			System.out.println("========================");



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
		NavigableSet<Integer> a = new TreeSet<>();
		for (ThreeWayBPlusTreeNode b: leafList)
		{
			for(int c : b.getKeyList())
			{
				a.add(c);
			}
		}
		return a.iterator();

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

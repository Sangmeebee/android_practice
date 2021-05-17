# DiffUtil

### notifyDataSetChanged 를 멀리해야하는 이유

- 비용이 많이 든다
- notifyItemChanged 가 있지만 정확한 position을 알아야한다



### DiffUtil 이란?

- 두 목록의 차이를 계산하고 old item에서 new Item으로 목록이 변환할 때 업데이트되는 작업 목록을 출력하는 유틸리티 클래스
- 추상메소드
  - areItemsTheSame( oldPosition:Int , newPosition:Int) : 두 객체가 동일한 항목을 나타내는지 확인
  - getOldListSize() : 바뀌 기 전 리스트의 크기를 리턴
  - getNewListSize() : 바뀐 후 리스트의 크기를 리턴
  - areContentsTheSame( oldPosition:Int, newPosition:Int) : 두 항목의 데이터가 같은지 확인

#### 구현방법

1. DiffUtil.Callback() 추상클래스를 상속받아 DiffUtil 구현
2. DiffUtil.calculateDiff(diffUtil) 메소드를 통해 업데이트가 필요한 리스트를 찾는다.
3. 기존 리스트를 업데이트
4. diffResult.dispatchUpdatesTo(this) 를 통해 교체가 필요한 아이템에 대해서 부분적으로 데이터를 교체하라는 notify가 실행



#### 아이템의 개수가 많을 경우 비교 연산 시간이 길어질 수 있기 때문에 calculateDiff는 백그라운드 쓰레드에서 처리되야 한다. 



## AsyncListDiff 이란?

- DiffUtil을 단순하게 사용할 수 있게 해주는 클래스
- 자체적으로 멀티 쓰레드에 대한 처리가 되어있기 때문에 개발자가 직접 동기화 처리를 하지 않아도 된다.
- Executors.newFixedThreadPool(2)로 쓰레드 풀을 하나 만들어서 비교 연산을 처리한다.



#### 구현방법

1. 아이템 비교시 호출할 DiffUtil.ItemCallback을 구현
2. Adapter 내부에 AsyncListDiffer 객체를 선언 
   AsyncListDiffer(this, DiffUtilCallback()) 
3. AsyncListDiffer 객체로 부터 getCurrentList(), submitList(List<T> newList) 와 같은 메소드 사용가능



## ListAdapter 란?

- AsyncListDiffer를 더욱 간편하게 사용할 수 있게 해준다.
- RecyclerView.Adapter 인터페이스에 맞춰 개발자가 손수 구현해야하는 기능들을 직접 구현해주지 않아도 된다. (ex) submitList(), getItem())
- 보일러 플레이트 코드가 확연히 줄어든다.
- getCurrentList(), onCurrentListChanged() // 리스트가 업데잉트 되었을 때 실행할 콜백 지정, submitList(MutableList<T> list) 등 메소드를 제공



### 구현방법

~~~kotlin
class RecyclerAdpater : ListAdapter<Item, ViewHolder>(DiffUtilCallback()) {
  overrride fun onBindViewHolder(...) {
    
  }
  
  override fun onCreateViewHolder(...) {
    
  }
  
  class DiffUtilCallback() : DiffUtil.ItemCallback<Item>(){
    
  }
}
~~~


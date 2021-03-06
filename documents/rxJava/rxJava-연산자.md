# RxJava - 연산자

- Data Set을 관찰가능하게 만들어주거나, 변환, 필터, 합성등 추가적인 작업을 할 수 있다.

## Create Operators 

### create()

- 하나의 객채만 가져와서 관찬가능한 객체로 만들어 주는 연산자

- input : T

- output : Observable<T>

- Emitter(발행자)를 이용하여 직접 아이템을 발행하고, 아이템 발행의 완료나 오류(Complete/Error)의 알림을 직접 설정할 수 있다.

  ~~~kotlin
  Observable<String> observable = Observable.create( emitter -> {
  	emitter.onNext("hi")
  	emitter.onNext("my name is Sangmin")
  	emitter.onComplete()  
  })
  ~~~

  

 fromIterable(), fromArray(), just(), range(), repeat()...

### Buffer

- 데이터를 바로 전달하지 않고 buffer size 만큼 모일 때 까지 기다렸다가 한 번에 List 형태로 전달하는 operator

  ~~~kotlin
  fun main(){
    Observable.fromIterable(0..10)
    .buffer(2, 3) // (count=2, skip=3)
    .subscribe{println(it)}
  }
  ~~~

  

### FlatMap

- Observable에서 데이터를 받아 새로운 Observable을 만드는 operator

- 들어온 데이터를 병렬적으로 처리

  ~~~kotlin
  fun main(){
    Observable.fromIterable(0..10)
    .flatMap{
      Observable.just("$it Hello $it World")
    }
    .subscribe{ println(it) }
  }
  
  /*
  0 Hello, 0 World
  1 Hello, 1 World
  2 Hello, 2 World
  3 Hello, 3 World
  ...
  10 Hello, 10 World
  */
  ~~~

<img src="/Users/sangmee/Library/Application Support/typora-user-images/스크린샷 2021-04-27 오전 1.33.20.png" alt="스크린샷 2021-04-27 오전 1.33.20" style="zoom:50%;" />



### ConcatMap 

- Observable에서 데이터를 받아 새로운 Observable을 만드는 Operator
- 들어온 데이터를 순차적으로 처리 (순서가 중요한 작업을 할 때 사용)

<img src="/Users/sangmee/Library/Application Support/typora-user-images/스크린샷 2021-04-27 오전 1.32.40.png" alt="스크린샷 2021-04-27 오전 1.32.40" style="zoom:50%;" />



### SwitchMap

- Observable에서 데이터를 받아 새로운 Observable을 만드는 Operator

- 중간에 새로운 데이터가 들어오면 이전 데이터를 더이상 배출하지 않는다.

  <img src="/Users/sangmee/Library/Application Support/typora-user-images/스크린샷 2021-04-27 오전 1.35.03.png" alt="스크린샷 2021-04-27 오전 1.35.03" style="zoom:50%;" />
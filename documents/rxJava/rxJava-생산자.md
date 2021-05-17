# RxJava - 생산자

### Observable

- 개념
  - 데이터의 흐름을 관장하는 클래스 (데이터를 관찰 할 수 있는 형태로 바꾸는 것이 이 클래스의 기본적인 임무)
  - 데이터의 흐름에 맞게 알림(onNext, onError, onComplete )을 보내 구독자가 데이터 처리를 할 수 있도록 한다.
  - 데이터를 Observer가 처리할 수 있도록 포장하는 작업

- 사용 방법
  1. Observable 만들기
  2. Observable에 연산자 적용
  3. 작업을 수행 할 스레드와 결과를 내보낼 스레드를 스케줄러를 통해 지정
  4. subscribe 메소드로 Observer를 Observable에 등록하거나 Consumer를 Observable에 등록



### Observer

- 개념
  - Observable에서 관찰 할 수 있는 형태로 전달한 데이터를 받고 이에 대한 행동을 취한다.



### Disposable (일회용)

- 개념
  - 더 이상 필요하지 않은 경우 반응형 데이터 스트림을 수신 할 필요가 없다.
  - 일회용으로 표시하면 관찰 대상에서 관찰자를 제거해주는 역할을 한다.

### Flowable

- 개념
  - 데이터의 흐름을 관장하는 클래스
  - **backPressure** 현상을 스스로 제어
  
- **backPressure(배압)** 이란?

  - 생산과 소비가 불균형적일 때 일어나는 현상
  - Observable이 데이터를 발행하는 속도를 Observer의 소비 속도가 따라가지 못하는 경우 결국 메모리가 overflow되고 OutOfMemoryExeption으로 이어진다. 이러한 현상을 backPressure라고 한다.

- **Flowable VS Observable**
  
  - Observable이 backPressure 현상을 제어하지 못했다면 Flowable은 이러한 현상을 스스로 제어할 수 있다.
  - `Observable`을 사용한 경우에는 데이터 발행과 소비가 균형적으로 일어나지 않으며 데이터는 소비와 상관없이 스트림에 계속 쌓이게 된다. (OOME 발생 가능성 있다.)
  - `Flowable`을 사용한 경우에는 데이터가 일정량 누적되면 데이터를 더이상 발행하지 않고 기다렸다가 다시 발행한다.
  - Observable 사용하는 경우
    - 1,000개 미만의 데이터 흐름이 발생하는 경우
    - 적은 데이터 소스만을 활용하여 OutOfMemoryException이 발생할 확률이 적은 경우
    - 마우스 이벤트나 터치 이벤트와 같은 GUI 프로그래밍을 하는 경우
    - Java Streams을 지원하지 않는 경우
  - Flowable 사용하는 경우
    - 10,000개 이상의 데이터 흐름이 발생하는 경우
    - 디스크에서 파일을 읽는 경우
    - 데이터베이스를 읽는 경우
    - 네트워크 IO 실행 시
  
- **BackPressure Strategy (배압 전략)**

  - Flowable에도 배압을 제어하지 못해 **MissingBackpressureException**이 발생할 수 있는 예외상황이 존재

    - Flowable과 interval()을 같이 사용하는 경우, interval 연산자는 스케줄러와 관계없이 시간에 의존해 데이터를 발행하므로 에러가 발생

  - Flowable에 배압 전략을 명시함으로써 배압을 제어할 수 있다.

  - <img src="/Users/sangmee/Library/Application Support/typora-user-images/스크린샷 2021-04-28 오전 2.58.19.png" alt="스크린샷 2021-04-28 오전 2.58.19" style="zoom:33%;" />

  - ~~~kotlin
    Flowable.create(emitter ->{
                   	for(i in 1..10000)emitter.onNext(i)
                    emitter.onComplete()
    }, BackPressureStrategy.DROP)
    ~~~

- 배압 제어 연산자

  - onBackPressureBuffer()
  - onBackPressureDrop()
  - onBackPressureLatest()


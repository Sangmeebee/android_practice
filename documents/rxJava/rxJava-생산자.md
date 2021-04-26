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
  - **backPressure**를 지원
- backPressure 이란?
  - 옵저버에게 너무 많은 데이터를 내보내고 옵저버가 처리 할 수없는 경우 **메모리 부족 예외**가 발생할 수 있다.
  - **Hot Source**
    - Observable은 Observer가 따라 잡을 수 있는지 여부에 관계없이 계속해서 데이터를 Observer쪽으로 **Push**한다.
    - Observable은 객체를 방출하며 이를 따라 잡는 것은 Observer에게 달려 있습니다.
  - **Cold Source**
    - Hot Source와 반대 개념
    - 방출 된 데이터는 전체 프로세스가 기본적으로 Observer의 재량에 있기 때문에 **버퍼링 할 필요가 없다.**
    - **Observer가 원할 때** Observable에서 데이터를 가져온다.
  - **Backpressure Strategies**
    - **onBackpressureBuffer()** : 무제한 버퍼 (JVM에 메모리가 부족하지 않는 한 모든 데이터 처리)
    - 다른 전략들 존재
- Observabler과 차이
  - backPressure를 지원 (Observable은 지원하지 않음)
  - subscribe 쪽에서 일처리가 늦어진다면 publish를 더이상 진행하지 않고 기다렸다가 진행한다.하지만 Observable은 신경쓰지 않고 데이터를 계속 생성하여 전달하고 소비자 쪽에서는 묵묵히 이 일을 처리해야함으로 너무 큰 데이터 스트림이 들어왔을 때 메모리 부족 예외 발생 가능성이 있다.




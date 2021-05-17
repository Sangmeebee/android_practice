# RxJava - 스케줄러

- RxJava의 코드가 **어느 스레드에서 실행될 것인지 지정**하는 역할
- 스케줄러를 통해 스레드를 분리해주어야 비동기 작업이 가능
- RxJava의 장점 중 하나가 특정 스케줄러를 사용하다가 다른 스케줄러로 변경하기 쉽다는 점이다.



### subscribeOn

- Observable이 데이터 흐름을 발생시키고 연산하는 스레드를 지정할 수 있다.



### observeOn

- Observable이 Observer에게 알림을 보내는 스레드를 지정할 수 있다.



### 스케줄러의 종류

- SINGLE
  - Schedulers.single() 
  - 단일 스레드를 생성해 계속 재사용하며 여러작업을 처리
  - RxJava 내부에서 스레드를 별도로 생성
  - 비동기 처리를 지향한다면 사용할 일이 거의 없다.
- COMPUTATION
  - Schedulars.computation()
  - IO 작업을 하지 않고 일반적인 계산 작업을 할 때 사용
  - 내부적으로 스레드 풀을 생성하고 생성된 스레드를 이용한다.
  - 스레드 개수 = 프로세스 개수
- IO
  - Schedulars.io()
  - 파일 입출력 등의 IO 작업이나 네트워크 요청 처리 시에 사용
  - 필요할 때마다 스레드를 계속 생성
- TRAMPOLINE
  - Schedulars.trampoline()
  - 현재 스레드에 무한한 크기의 대기 큐 생성
- NEW_THREAD
  - schedulars.newThread()
  - 요청을 받을 때 마다 매번 새로운 스레드 생성
- Thread Pool을 직접 생성하여 RxJava에서 사용 가능
  - Executor을 통해 Thread Pool 생성
  - Executor.newFixedThreadPool(10)
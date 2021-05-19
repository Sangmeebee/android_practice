# dagger2



## DI

- Dagger는 **DI Framework** 이다.
- A Class가 B Class를 의존할 때 B Object를 A가 직접 생성하지 않고 외부에서 생성하여 넘겨주면 의존성을 주입했다고 한다.
- 외부에서 객체를 넘겨주는 것을 Spring에서는 container **Dagger에서는 Component, Module** 이라고 한다.



### DI는 왜 필요?

- 보일러 플레이트 코드를 많이 줄일 수 있다.
- Interface에 구현체를 쉽게 교체하면서 상황에 따라 적절한 행동을 정의 할 수 있다.



## Dagger

### 1. Inject

- 의존성 주입을 요청
- 연결된 Component가 Module로 부터 객체를 생성하여 넘겨준다.



### 2. Component

- 연결된 Module을 이용하여 의존성 객체를 생성하고, Inject로 요청받은 인스턴스에 생성한 객체를 주입한다.
- 의존성을 요청받고 주입하는 Dagger의 주된 역할을 수행



### 3. SubComponent

- Component의 계층관계를 만든다.
- Inner Class 방식의 하위계층 Component
- 그래프를 형성
- Inject로 주입 요청을 받으면 SubComponent에서 먼저 의존성을 검색하고, 없으면 부모로 올라가면서 검색



### 4. Module

- Component에 연결되어 의존성 객체를 생성한다.
- Scope에 따라 관리



### 5. Scope

- 생성된 객체의 라이프사이클 범위
- PerActivity, PerFragment 등으로 화면의 생명주기와 맞추어 사용
- Module에서 Scope를 보고 객체를 관리


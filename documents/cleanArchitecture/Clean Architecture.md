# Clean Architecture

## MVC

- Model = 상태 + 비즈니스 로직 + 데이터
- View = UI (xml, html)
- Controller = flow관리, 사용자 입력에 따른 응답으로 Model이나 View를 업데이트하는 로직을 포함한다.



### 안드로이드에서 MVC가 나쁜 이유

- View와 Controller 분리가 애매
  - Activity와 Fragment에 너무 많은 로직이 들어간다
  - Unit Test를 만들기 매우 까다롭다
- 가독성이 떨어지고 클래스 마다 너무 많은 역할을 담당하여 유지보수하기 어렵다



## 안드로이드에서의 Clean Architecture

- 재사용성 / 가독성 / 유지보수를 위해 사용
- 변화에 잘 대응 하기 위한 코드 설계 방법 (변화가 일어나는 곳에서만 코드의 변화가 일어나야한다. SRP)
- 

### 기본적인 개념

![스크린샷 2021-05-25 오후 7.58.39](/Users/sangmee/Library/Application Support/typora-user-images/스크린샷 2021-05-25 오후 7.58.39.png)

- MVC에서 Model을 분해 - Domain Layer + Data Layer
- Presentation Layer의 **Presenter** (사용자에게 보여지는 로직과 관련) 와 Domain Layer의 **UseCase**로 비즈니스 로직 나누어서 처리
- **도메인 로직이 차지하는 부분이 상대적으로 적다** - 대부분의 앱의 경우, 복잡한 비즈니스 로직은 백엔드 서버에 존재한다.
- Presentation Logic에 주로 주목하는 이유 : **비동기적인 상태 관리**, **라이프 사이클 연동**이 훨씬 복잡하고 중요한 문제 
- 모바일 아키텍처에서 가장 큰 난제는 **상태변화와 UI 사이의 결합을 어떻게 시켜줄지**가 가장 큰 문제



### Non-MVC 설계 전략

- Activity는 Controller임을 인지
- MVP 접근법
  - Activity에서 View와 Controller의 역할을 최대한 뺏어 제 3의 View와 Presenter로 넘김
  - Presenter에서는 뷰에 데이터를 세팅해주거나, 데이터를 model layer로 보내 연산작업을 하는 식의 작업을 함
  - Activity는 순수 flow 관리 역할 위주
- MVVM 접근법
  - Activity는 최대한 context 의존 기능만 하도록
  - View logic은 최대한 data binding으로 구현
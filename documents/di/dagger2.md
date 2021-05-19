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
- 주입하기 위한 생성자를 정의
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



### 사용방법

- AppComponent

  ~~~kotlin
  @Singleton  // Scope
  // 연결할 Module을 정의합니다.
  @Component(modules = {AndroidSupportInjectionModule.class,
          ActivityBindingModule.class,
          ApplicationModule.class})
  // Application과의 연결을 도울 AndroidInjector를 상속받고, 제네릭으로 BaseApplication 클래스를 정의합니다.
  public interface AppComponent extends AndroidInjector<BaseApplication> {
  
      // Application과의 연결을 도울 Builder를 정의합니다.
      @Component.Builder
      interface Builder {
          @BindsInstance
          AppComponent.Builder application(Application application);
          AppComponent build();
      }
  }
  ~~~

  

- AppModule

  ~~~kotlin
  @Module
  public class ApplicationModule {
      // Context 타입의 의존성 객체를 생성합니다.
      @Provides
      Context providesContext(Application application){
          return application;
      }
  }
  ~~~

  Provides 어노테이션으로 의존성 객체를 생성할 메소드를 정의합니다. 반환 타입을 따라 Component가 검색하여 활용합니다.



- BaseApplication에서 Component 연동하기

  ~~~kotlin
  // DaggerApplication를 상속받고, ApplicationComponent에서 정의한 Builder를 활용하여 Component와 연결합니다.
  public class BaseApplication extends DaggerApplication {
      @Override
      protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
          return DaggerAppComponent.builder().application(this).build();
      }
  }
  ~~~



- ActivitySubcomponent 생성하기

  ~~~kotlin
  // ActivityBindingModule은 위 ApplicationComponent에 연결되어 있습니다.
  @Module
  public abstract class ActivityBindingModule {
      // ContributesAndroidInjector 어노테이션을 달고 반환타입을 통해 해당 Activity의 Subcomponent를 생성합니다.
      // modules에 Subcomponent와 연결할 Module을 정의합니다. 이 Module들이 실제 의존성 객체를 생성합니다.
      @ActivityScoped  // Scope
      @ContributesAndroidInjector(modules = TasksModule.class)
      abstract TasksActivity tasksActivity();
  
      @ActivityScoped
      @ContributesAndroidInjector(modules = TaskDetailPresenterModule.class)
      abstract TaskDetailActivity taskDetailActivity();
  }
  ~~~

  원래 Subcomponent는 어노테이션을 활용하여 직접 만들었어야 했지만, 이제 **ContributesAndroidInjector를 활용하여 Module에서 자동으로 Subcomponent 생성**할 수 있습니다.

- ActivitySubcomponent의 Module

  ~~~kotlin
  @Module
  public abstract class TasksModule {
      // ContributesAndroidInjector로 FragmentSubcomponent를 생성합니다.
      @FragmentScoped  // Scope
      @ContributesAndroidInjector
      abstract TasksFragment tasksFragment();
  
      // TasksPresenter 타입의 의존성 객체를 생성합니다.
      @ActivityScoped  // Scope
      @Provides
      static TasksPresenter taskPresenter(){
          return new TasksPresenter();
      }
  }
  ~~~

  ApplicationModule와 마찬가지로 Provides으로 의존성 객체를 생성할 메소드를 정의합니다. 그리고 **ContributesAndroidInjector로 하위 Fragment의 Subcomponent를 생성**합니다.



- Activity에서 Component 연동

  ~~~kotlin
  // DaggerAppCompatActivity를 상속받아 Component를 연결합니다.
  public class TasksActivity extends DaggerAppCompatActivity { 
      // TasksPresenter 타입의 의존성 주입을 요청합니다.
      @Inject
      TasksPresenter mTasksPresenter;
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.tasks_act);
      }
  }
  
  ~~~

  

- Fragment에서 Component 연동

  ~~~kotlin
  // DaggerFragment를 상속받아 Component를 연결합니다.
  @ActivityScoped  // Fragment는 Activity에 속하므로 Activity Scope를 정의하였습니다.
  public class TasksFragment extends DaggerFragment {
      // TasksContract.Presenter 타입의 의존성 주입을 요청합니다.
      @Inject
      TasksContract.Presenter mPresenter;
      @Override
      public void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
      }
  }
  ~~~

  
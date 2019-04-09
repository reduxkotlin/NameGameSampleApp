#import <Foundation/Foundation.h>

@class CommonINIT, CommonDispatcherFn, CommonReduksContext, CommonReduksCompanion, CommonReduksInternalLogUtils, CommonKotlinThrowable, CommonSimpleStore, CommonKotlinUnit, CommonSimpleStoreCreator, CommonSimpleStoreCompanion, CommonStoreCreatorWithMiddlewares, CommonKotlinArray, CommonStoreEnhancerImpl, CommonStoreSubscriberImpl, CommonStoreSubscriberBuilderImpl, CommonStoreSubscriberBuilderImpl2, CommonSelectorBuilder, CommonThunkImpl, CommonSelectorSubscriberBuilder, CommonOpt, CommonInputField, CommonAbstractSelector, CommonSelectorForP5, CommonSelectorForP4, CommonSelectorForP3, CommonSelectorForP2, CommonSelectorForP1, CommonActionWithContext, CommonActionWithContextLambdaPattern, CommonReduksContextCompanion, CommonReduksContextTyped, CommonActions, CommonActionsFetchingProfilesStartedAction, CommonActionsFetchingProfilesSuccessAction, CommonProfile, CommonActionsFetchingProfilesFailedAction, CommonActionsNamePickedAction, CommonActionsNextQuestionAction, CommonActionsGameCompleteAction, CommonActionsStartOverAction, CommonActionsResetGameStateAction, CommonActionsStartQuestionTimerAction, CommonActionsDecrementCountDownAction, CommonActionsTimesUpAction, CommonActionsSettingsTappedAction, CommonActionsLoadAllSettingsAction, CommonActionsChangeNumQuestionsSettingsAction, CommonAppState, CommonQuestion, CommonUserSettings, CommonAppStateCompanion, CommonQuestionStatus, CommonKotlinEnum, CommonUserSettingsCompanion, CommonGameEngine, CommonPresenter, CommonVibrateUtil, CommonNetworkThunks, CommonTimerThunks, CommonViewEffect, CommonViewStates, CommonSettingsViewState, CommonQuestionViewState, CommonGameResultsViewState, CommonPlatformLogger, CommonLogger, CommonPlatformDispatcher, CommonKotlinx_coroutines_core_nativeCoroutineDispatcher, CommonKotlinAbstractCoroutineContextElement, CommonUI, CommonGameResultsPresenter, CommonQuestionPresenter, CommonSettingsPresenter, CommonStartPresenter, CommonTimeUtil, CommonGatewayResponse, CommonGatewayResponseCompanion, CommonGenericError, CommonKtorProfilesRepository, CommonProfileListHolder, CommonProfileListHolderSerializer, CommonKotlinx_serialization_runtime_nativeSerialClassDescImpl, CommonMockRepositoryFactory, CommonMockRepositoryFactoryCompanion, CommonHeadshot, CommonSocialLinks, CommonProfileCompanion, CommonProfile$serializer, CommonHeadshotCompanion, CommonHeadshot$serializer, CommonSocialLinksCompanion, CommonSocialLinks$serializer, CommonLocalStorageSettingsRepository, CommonLocalStorageSettingsRepositoryCompanion, CommonNavigationMiddleware, CommonScreen, CommonSettingsMiddleware, CommonViewEffectsMiddleware, CommonKotlinException, CommonKotlinx_serialization_runtime_nativeEnumDescriptor, CommonKotlinx_serialization_runtime_nativeSerialKind, CommonKotlinNothing, CommonKotlinx_serialization_runtime_nativeUpdateMode;

@protocol CommonAction, CommonReduks, CommonStore, CommonStoreSubscription, CommonSagaAction, CommonStoreSubscriber, CommonStoreCreator, CommonStandardAction, CommonStandardActionM, CommonStoreEnhancer, CommonStoreSubscriberBuilder, CommonThunk, CommonSelector, CommonMemoizer, CommonSelectorInput, CommonActionWithContextPattern, CommonKotlinComparable, CommonNavigator, CommonKotlinCoroutineContext, CommonView, CommonKotlinx_coroutines_core_nativeCoroutineScope, CommonKotlinCoroutineContextElement, CommonKotlinCoroutineContextKey, CommonKotlinContinuationInterceptor, CommonKotlinContinuation, CommonKotlinx_coroutines_core_nativeRunnable, CommonKotlinx_coroutines_core_nativeDelay, CommonKotlinx_coroutines_core_nativeDisposableHandle, CommonKotlinx_coroutines_core_nativeCancellableContinuation, CommonGameResultsView, CommonQuestionView, CommonSettingsView, CommonStartView, CommonProfilesRepository, CommonKotlinx_serialization_runtime_nativeKSerializer, CommonKotlinx_serialization_runtime_nativeSerializationStrategy, CommonKotlinx_serialization_runtime_nativeEncoder, CommonKotlinx_serialization_runtime_nativeSerialDescriptor, CommonKotlinx_serialization_runtime_nativeDeserializationStrategy, CommonKotlinx_serialization_runtime_nativeDecoder, CommonKotlinx_serialization_runtime_nativeGeneratedSerializer, CommonMultiplatform_settingsSettings, CommonKotlinIterator, CommonKotlinx_serialization_runtime_nativeCompositeEncoder, CommonKotlinx_serialization_runtime_nativeSerialContext, CommonKotlinAnnotation, CommonKotlinx_serialization_runtime_nativeCompositeDecoder, CommonMultiplatform_settingsSettingsListener, CommonKotlinKClass, CommonKotlinKDeclarationContainer, CommonKotlinKAnnotatedElement, CommonKotlinKClassifier;

NS_ASSUME_NONNULL_BEGIN

@interface KotlinBase : NSObject
- (instancetype)init __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (void)initialize __attribute__((objc_requires_super));
@end;

@interface KotlinBase (KotlinBaseCopying) <NSCopying>
@end;

__attribute__((objc_runtime_name("KotlinMutableSet")))
__attribute__((swift_name("KotlinMutableSet")))
@interface CommonMutableSet<ObjectType> : NSMutableSet<ObjectType>
@end;

__attribute__((objc_runtime_name("KotlinMutableDictionary")))
__attribute__((swift_name("KotlinMutableDictionary")))
@interface CommonMutableDictionary<KeyType, ObjectType> : NSMutableDictionary<KeyType, ObjectType>
@end;

@interface NSError (NSErrorKotlinException)
@property (readonly) id _Nullable kotlinException;
@end;

__attribute__((objc_runtime_name("KotlinNumber")))
__attribute__((swift_name("KotlinNumber")))
@interface CommonNumber : NSNumber
- (instancetype)initWithChar:(char)value __attribute__((unavailable));
- (instancetype)initWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
- (instancetype)initWithShort:(short)value __attribute__((unavailable));
- (instancetype)initWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
- (instancetype)initWithInt:(int)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
- (instancetype)initWithLong:(long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
- (instancetype)initWithLongLong:(long long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
- (instancetype)initWithFloat:(float)value __attribute__((unavailable));
- (instancetype)initWithDouble:(double)value __attribute__((unavailable));
- (instancetype)initWithBool:(BOOL)value __attribute__((unavailable));
- (instancetype)initWithInteger:(NSInteger)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
+ (instancetype)numberWithChar:(char)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
+ (instancetype)numberWithShort:(short)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
+ (instancetype)numberWithInt:(int)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
+ (instancetype)numberWithLong:(long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
+ (instancetype)numberWithLongLong:(long long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
+ (instancetype)numberWithFloat:(float)value __attribute__((unavailable));
+ (instancetype)numberWithDouble:(double)value __attribute__((unavailable));
+ (instancetype)numberWithBool:(BOOL)value __attribute__((unavailable));
+ (instancetype)numberWithInteger:(NSInteger)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
@end;

__attribute__((objc_runtime_name("KotlinByte")))
__attribute__((swift_name("KotlinByte")))
@interface CommonByte : CommonNumber
- (instancetype)initWithChar:(char)value;
+ (instancetype)numberWithChar:(char)value;
@end;

__attribute__((objc_runtime_name("KotlinUByte")))
__attribute__((swift_name("KotlinUByte")))
@interface CommonUByte : CommonNumber
- (instancetype)initWithUnsignedChar:(unsigned char)value;
+ (instancetype)numberWithUnsignedChar:(unsigned char)value;
@end;

__attribute__((objc_runtime_name("KotlinShort")))
__attribute__((swift_name("KotlinShort")))
@interface CommonShort : CommonNumber
- (instancetype)initWithShort:(short)value;
+ (instancetype)numberWithShort:(short)value;
@end;

__attribute__((objc_runtime_name("KotlinUShort")))
__attribute__((swift_name("KotlinUShort")))
@interface CommonUShort : CommonNumber
- (instancetype)initWithUnsignedShort:(unsigned short)value;
+ (instancetype)numberWithUnsignedShort:(unsigned short)value;
@end;

__attribute__((objc_runtime_name("KotlinInt")))
__attribute__((swift_name("KotlinInt")))
@interface CommonInt : CommonNumber
- (instancetype)initWithInt:(int)value;
+ (instancetype)numberWithInt:(int)value;
@end;

__attribute__((objc_runtime_name("KotlinUInt")))
__attribute__((swift_name("KotlinUInt")))
@interface CommonUInt : CommonNumber
- (instancetype)initWithUnsignedInt:(unsigned int)value;
+ (instancetype)numberWithUnsignedInt:(unsigned int)value;
@end;

__attribute__((objc_runtime_name("KotlinLong")))
__attribute__((swift_name("KotlinLong")))
@interface CommonLong : CommonNumber
- (instancetype)initWithLongLong:(long long)value;
+ (instancetype)numberWithLongLong:(long long)value;
@end;

__attribute__((objc_runtime_name("KotlinULong")))
__attribute__((swift_name("KotlinULong")))
@interface CommonULong : CommonNumber
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value;
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value;
@end;

__attribute__((objc_runtime_name("KotlinFloat")))
__attribute__((swift_name("KotlinFloat")))
@interface CommonFloat : CommonNumber
- (instancetype)initWithFloat:(float)value;
+ (instancetype)numberWithFloat:(float)value;
@end;

__attribute__((objc_runtime_name("KotlinDouble")))
__attribute__((swift_name("KotlinDouble")))
@interface CommonDouble : CommonNumber
- (instancetype)initWithDouble:(double)value;
+ (instancetype)numberWithDouble:(double)value;
@end;

__attribute__((objc_runtime_name("KotlinBoolean")))
__attribute__((swift_name("KotlinBoolean")))
@interface CommonBoolean : CommonNumber
- (instancetype)initWithBool:(BOOL)value;
+ (instancetype)numberWithBool:(BOOL)value;
@end;

__attribute__((swift_name("Action")))
@protocol CommonAction
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("INIT")))
@interface CommonINIT : KotlinBase <CommonAction>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DispatcherFn")))
@interface CommonDispatcherFn : KotlinBase
- (instancetype)initWithFn:(id (^)(id))fn __attribute__((swift_name("init(fn:)"))) __attribute__((objc_designated_initializer));
- (id _Nullable)dispatchAction:(id)action __attribute__((swift_name("dispatch(action:)")));
- (id _Nullable)invokeAction:(id)action __attribute__((swift_name("invoke(action:)")));
@property (readonly) id (^ _Nullable fn_)(id);
@property (readonly) id (^ _Nullable fn)(id);
@end;

__attribute__((swift_name("Reduks")))
@protocol CommonReduks
@required
@property (readonly) CommonReduksContext *ctx;
@property (readonly) id<CommonStore> store;
@property (readonly) CommonMutableDictionary<NSString *, id<CommonStoreSubscription>> *storeSubscriptionsByTag;
@property (readonly) CommonMutableDictionary<NSString *, NSMutableArray<id<CommonStoreSubscription>> *> *busStoreSubscriptionsByTag;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReduksCompanion")))
@interface CommonReduksCompanion : KotlinBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (readonly) NSString *TagMainSubscription;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReduksInternalLogUtils")))
@interface CommonReduksInternalLogUtils : KotlinBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)reduksInternalLogUtils __attribute__((swift_name("init()")));
- (void)reportErrorInReducerS:(id<CommonStore>)s e:(CommonKotlinThrowable *)e __attribute__((swift_name("reportErrorInReducer(s:e:)")));
- (void)reportErrorInSubscriberS:(id<CommonStore>)s e:(CommonKotlinThrowable *)e __attribute__((swift_name("reportErrorInSubscriber(s:e:)")));
@end;

__attribute__((swift_name("SagaAction")))
@protocol CommonSagaAction
@required
@end;

__attribute__((swift_name("Store")))
@protocol CommonStore
@required
- (id<CommonStoreSubscription>)subscribeStoreSubscriber:(id<CommonStoreSubscriber>)storeSubscriber __attribute__((swift_name("subscribe(storeSubscriber:)")));
- (void)replaceReducerReducer:(id _Nullable (^)(id _Nullable, id))reducer __attribute__((swift_name("replaceReducer(reducer:)")));
@property (readonly) id _Nullable state;
@property id (^dispatch)(id);
@property CommonKotlinUnit *(^ _Nullable errorLogFn)(NSString *);
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SimpleStore")))
@interface CommonSimpleStore : KotlinBase <CommonStore>
- (instancetype)initWithInitialState:(id _Nullable)initialState reducer:(id _Nullable (^)(id _Nullable, id))reducer __attribute__((swift_name("init(initialState:reducer:)"))) __attribute__((objc_designated_initializer));
@property id _Nullable state;
@end;

__attribute__((swift_name("StoreCreator")))
@protocol CommonStoreCreator
@required
- (id<CommonStore>)createReducer:(id _Nullable (^)(id _Nullable, id))reducer initialState:(id _Nullable)initialState __attribute__((swift_name("create(reducer:initialState:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SimpleStore.Creator")))
@interface CommonSimpleStoreCreator : KotlinBase <CommonStoreCreator>
- (instancetype)initWithWithStandardMiddlewares:(BOOL)withStandardMiddlewares __attribute__((swift_name("init(withStandardMiddlewares:)"))) __attribute__((objc_designated_initializer));
@property (readonly) BOOL withStandardMiddlewares;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SimpleStore.Companion")))
@interface CommonSimpleStoreCompanion : KotlinBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (readonly) NSString *redukstag;
@end;

__attribute__((swift_name("StandardAction")))
@protocol CommonStandardAction <CommonAction>
@required
@property (readonly) id _Nullable payload;
@property (readonly) BOOL error;
@end;

__attribute__((swift_name("StandardActionM")))
@protocol CommonStandardActionM <CommonStandardAction>
@required
@property (readonly) id _Nullable meta;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("StoreCreatorWithMiddlewares")))
@interface CommonStoreCreatorWithMiddlewares : KotlinBase <CommonStoreCreator>
- (instancetype)initWithCreator:(id<CommonStoreCreator>)creator middlewares_:(CommonKotlinArray *)middlewares_ __attribute__((swift_name("init(creator:middlewares_:)"))) __attribute__((objc_designated_initializer));
@property (readonly) CommonKotlinArray *middlewares;
@property (readonly) id<CommonStoreCreator> creator;
@end;

__attribute__((swift_name("StoreEnhancer")))
@protocol CommonStoreEnhancer
@required
- (id<CommonStoreCreator>)enhanceNext:(id<CommonStoreCreator>)next __attribute__((swift_name("enhance(next:)")));
@end;

__attribute__((swift_name("StoreSubscriber")))
@protocol CommonStoreSubscriber
@required
- (void)onStateChange __attribute__((swift_name("onStateChange()")));
@end;

__attribute__((swift_name("StoreSubscriberBuilder")))
@protocol CommonStoreSubscriberBuilder
@required
- (id<CommonStoreSubscriber>)buildStore:(id<CommonStore>)store __attribute__((swift_name("build(store:)")));
@end;

__attribute__((swift_name("StoreSubscription")))
@protocol CommonStoreSubscription
@required
- (void)unsubscribe __attribute__((swift_name("unsubscribe()")));
@end;

__attribute__((swift_name("Thunk")))
@protocol CommonThunk <CommonAction>
@required
- (id)executeDispatcher:(id (^)(id))dispatcher state:(id _Nullable)state __attribute__((swift_name("execute(dispatcher:state:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("StoreEnhancerImpl")))
@interface CommonStoreEnhancerImpl : KotlinBase <CommonStoreEnhancer>
- (instancetype)initWithStoreEnhancerFn:(id<CommonStoreCreator> (^)(id<CommonStoreCreator>))storeEnhancerFn __attribute__((swift_name("init(storeEnhancerFn:)"))) __attribute__((objc_designated_initializer));
@property (readonly) id<CommonStoreCreator> (^storeEnhancerFn)(id<CommonStoreCreator>);
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("StoreSubscriberImpl")))
@interface CommonStoreSubscriberImpl : KotlinBase <CommonStoreSubscriber>
- (instancetype)initWithSubscriberFn:(CommonKotlinUnit *(^)(void))subscriberFn __attribute__((swift_name("init(subscriberFn:)"))) __attribute__((objc_designated_initializer));
@property (readonly) CommonKotlinUnit *(^subscriberFn)(void);
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("StoreSubscriberBuilderImpl")))
@interface CommonStoreSubscriberBuilderImpl : KotlinBase <CommonStoreSubscriberBuilder>
- (instancetype)initWithStoreSubscriberBuilderFn:(id<CommonStoreSubscriber> (^)(id<CommonStore>))storeSubscriberBuilderFn __attribute__((swift_name("init(storeSubscriberBuilderFn:)"))) __attribute__((objc_designated_initializer));
@property (readonly) id<CommonStoreSubscriber> (^storeSubscriberBuilderFn)(id<CommonStore>);
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("StoreSubscriberBuilderImpl2")))
@interface CommonStoreSubscriberBuilderImpl2 : KotlinBase <CommonStoreSubscriberBuilder>
- (instancetype)initWithStoreSubscriberBuilderFn2:(id<CommonStoreSubscriber> (^)(id<CommonStore>, CommonSelectorBuilder *))storeSubscriberBuilderFn2 __attribute__((swift_name("init(storeSubscriberBuilderFn2:)"))) __attribute__((objc_designated_initializer));
@property (readonly) CommonSelectorBuilder *selector;
@property (readonly) id<CommonStoreSubscriber> (^storeSubscriberBuilderFn2)(id<CommonStore>, CommonSelectorBuilder *);
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ThunkImpl")))
@interface CommonThunkImpl : KotlinBase <CommonThunk>
- (instancetype)initWithThunkFn:(id (^)(id (^)(id), id _Nullable))thunkFn __attribute__((swift_name("init(thunkFn:)"))) __attribute__((objc_designated_initializer));
@property (readonly) id (^thunkFn)(id (^)(id), id _Nullable);
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SelectorSubscriberBuilder")))
@interface CommonSelectorSubscriberBuilder : KotlinBase
- (instancetype)initWithStore:(id<CommonStore>)store __attribute__((swift_name("init(store:)"))) __attribute__((objc_designated_initializer));
- (void)withAnyChangeF:(CommonKotlinUnit *(^)(void))f __attribute__((swift_name("withAnyChange(f:)")));
- (void)withSingleFieldSelector:(id (^)(id))selector action:(CommonKotlinUnit *(^)(id))action __attribute__((swift_name("withSingleField(selector:action:)")));
@property (readonly) id state;
@property CommonKotlinUnit *(^ _Nullable withAnyChangeFun)(void);
@property (readonly) CommonMutableDictionary<id<CommonSelector>, CommonKotlinUnit *(^)(id)> *selectorList;
@property (readonly) id<CommonStore> store;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Opt")))
@interface CommonOpt : KotlinBase
- (instancetype)initWithIt:(id _Nullable)it __attribute__((swift_name("init(it:)"))) __attribute__((objc_designated_initializer));
@property (readonly) id _Nullable it;
@end;

__attribute__((swift_name("Memoizer")))
@protocol CommonMemoizer
@required
- (id _Nullable)memoizeState:(id)state inputs:(CommonKotlinArray *)inputs __attribute__((swift_name("memoize(state:inputs:)")));
@end;

__attribute__((swift_name("SelectorInput")))
@protocol CommonSelectorInput
@required
- (id _Nullable)invokeState:(id _Nullable)state __attribute__((swift_name("invoke(state:)")));
@property (readonly) CommonBoolean *(^equalityCheck)(id, id);
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("InputField")))
@interface CommonInputField : KotlinBase <CommonSelectorInput>
- (instancetype)initWithFn:(id _Nullable (^)(id _Nullable))fn equalityCheck:(CommonBoolean *(^)(id, id))equalityCheck __attribute__((swift_name("init(fn:equalityCheck:)"))) __attribute__((objc_designated_initializer));
@property (readonly) id _Nullable (^fn)(id _Nullable);
@end;

__attribute__((swift_name("Selector")))
@protocol CommonSelector <CommonSelectorInput>
@required
- (BOOL)isChanged __attribute__((swift_name("isChanged()")));
- (void)signalChanged __attribute__((swift_name("signalChanged()")));
- (void)resetChanged __attribute__((swift_name("resetChanged()")));
- (id _Nullable)getIfChangedInState:(id _Nullable)state __attribute__((swift_name("getIfChangedIn(state:)")));
- (void)onChangeInState:(id _Nullable)state blockfn:(CommonKotlinUnit *(^)(id _Nullable))blockfn __attribute__((swift_name("onChangeIn(state:blockfn:)")));
- (void)onChangeInState:(id _Nullable)state condition:(BOOL)condition blockfn:(CommonKotlinUnit *(^)(id _Nullable))blockfn __attribute__((swift_name("onChangeIn(state:condition:blockfn:)")));
@property (readonly) int64_t recomputations;
@end;

__attribute__((swift_name("AbstractSelector")))
@interface CommonAbstractSelector : KotlinBase <CommonSelector>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property int64_t recomputationsLastChanged;
@property int64_t _recomputations;
@property (readonly) id _Nullable (^computeAndCount)(CommonKotlinArray *);
@property (readonly) id<CommonMemoizer> memoizer;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SelectorForP5")))
@interface CommonSelectorForP5 : KotlinBase
- (instancetype)initWithSi0:(id<CommonSelectorInput>)si0 si1:(id<CommonSelectorInput>)si1 si2:(id<CommonSelectorInput>)si2 si3:(id<CommonSelectorInput>)si3 si4:(id<CommonSelectorInput>)si4 __attribute__((swift_name("init(si0:si1:si2:si3:si4:)"))) __attribute__((objc_designated_initializer));
- (CommonAbstractSelector *)computeEqualityCheckForResult:(CommonBoolean *(^)(id, id))equalityCheckForResult computeFun:(id _Nullable (^)(id, id, id, id, id))computeFun __attribute__((swift_name("compute(equalityCheckForResult:computeFun:)")));
@property (readonly) id<CommonSelectorInput> si0;
@property (readonly) id<CommonSelectorInput> si1;
@property (readonly) id<CommonSelectorInput> si2;
@property (readonly) id<CommonSelectorInput> si3;
@property (readonly) id<CommonSelectorInput> si4;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SelectorForP4")))
@interface CommonSelectorForP4 : KotlinBase
- (instancetype)initWithSi0:(id<CommonSelectorInput>)si0 si1:(id<CommonSelectorInput>)si1 si2:(id<CommonSelectorInput>)si2 si3:(id<CommonSelectorInput>)si3 __attribute__((swift_name("init(si0:si1:si2:si3:)"))) __attribute__((objc_designated_initializer));
- (CommonSelectorForP5 *)withFieldFn:(id (^)(id))fn __attribute__((swift_name("withField(fn:)")));
- (CommonSelectorForP5 *)withFieldByValueFn:(id (^)(id))fn __attribute__((swift_name("withFieldByValue(fn:)")));
- (CommonSelectorForP5 *)withSelectorSi:(id<CommonSelectorInput>)si __attribute__((swift_name("withSelector(si:)")));
- (CommonAbstractSelector *)computeEqualityCheckForResult:(CommonBoolean *(^)(id, id))equalityCheckForResult computeFun:(id _Nullable (^)(id, id, id, id))computeFun __attribute__((swift_name("compute(equalityCheckForResult:computeFun:)")));
@property (readonly) id<CommonSelectorInput> si0;
@property (readonly) id<CommonSelectorInput> si1;
@property (readonly) id<CommonSelectorInput> si2;
@property (readonly) id<CommonSelectorInput> si3;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SelectorForP3")))
@interface CommonSelectorForP3 : KotlinBase
- (instancetype)initWithSi0:(id<CommonSelectorInput>)si0 si1:(id<CommonSelectorInput>)si1 si2:(id<CommonSelectorInput>)si2 __attribute__((swift_name("init(si0:si1:si2:)"))) __attribute__((objc_designated_initializer));
- (CommonSelectorForP4 *)withFieldFn:(id (^)(id))fn __attribute__((swift_name("withField(fn:)")));
- (CommonSelectorForP4 *)withFieldByValueFn:(id (^)(id))fn __attribute__((swift_name("withFieldByValue(fn:)")));
- (CommonSelectorForP4 *)withSelectorSi:(id<CommonSelectorInput>)si __attribute__((swift_name("withSelector(si:)")));
- (CommonAbstractSelector *)computeEqualityCheckForResult:(CommonBoolean *(^)(id, id))equalityCheckForResult computeFun:(id _Nullable (^)(id, id, id))computeFun __attribute__((swift_name("compute(equalityCheckForResult:computeFun:)")));
@property (readonly) id<CommonSelectorInput> si0;
@property (readonly) id<CommonSelectorInput> si1;
@property (readonly) id<CommonSelectorInput> si2;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SelectorForP2")))
@interface CommonSelectorForP2 : KotlinBase
- (instancetype)initWithSi0:(id<CommonSelectorInput>)si0 si1:(id<CommonSelectorInput>)si1 __attribute__((swift_name("init(si0:si1:)"))) __attribute__((objc_designated_initializer));
- (CommonSelectorForP3 *)withFieldFn:(id (^)(id))fn __attribute__((swift_name("withField(fn:)")));
- (CommonSelectorForP3 *)withFieldByValueFn:(id (^)(id))fn __attribute__((swift_name("withFieldByValue(fn:)")));
- (CommonSelectorForP3 *)withSelectorSi:(id<CommonSelectorInput>)si __attribute__((swift_name("withSelector(si:)")));
- (CommonAbstractSelector *)computeEqualityCheckForResult:(CommonBoolean *(^)(id, id))equalityCheckForResult computeFun:(id _Nullable (^)(id, id))computeFun __attribute__((swift_name("compute(equalityCheckForResult:computeFun:)")));
@property (readonly) id<CommonSelectorInput> si0;
@property (readonly) id<CommonSelectorInput> si1;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SelectorForP1")))
@interface CommonSelectorForP1 : KotlinBase
- (instancetype)initWithSi0:(id<CommonSelectorInput>)si0 __attribute__((swift_name("init(si0:)"))) __attribute__((objc_designated_initializer));
- (CommonSelectorForP2 *)withFieldFn:(id (^)(id))fn __attribute__((swift_name("withField(fn:)")));
- (CommonSelectorForP2 *)withFieldByValueFn:(id (^)(id))fn __attribute__((swift_name("withFieldByValue(fn:)")));
- (CommonSelectorForP2 *)withSelectorSi:(id<CommonSelectorInput>)si __attribute__((swift_name("withSelector(si:)")));
- (CommonAbstractSelector *)computeEqualityCheckForResult:(CommonBoolean *(^)(id, id))equalityCheckForResult computeFun:(id _Nullable (^)(id))computeFun __attribute__((swift_name("compute(equalityCheckForResult:computeFun:)")));
@property (readonly) id<CommonSelectorInput> si0;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SelectorBuilder")))
@interface CommonSelectorBuilder : KotlinBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (CommonSelectorForP1 *)withFieldFn:(id (^)(id))fn __attribute__((swift_name("withField(fn:)")));
- (CommonSelectorForP1 *)withFieldByValueFn:(id (^)(id))fn __attribute__((swift_name("withFieldByValue(fn:)")));
- (CommonSelectorForP1 *)withSelectorSi:(id<CommonSelectorInput>)si __attribute__((swift_name("withSelector(si:)")));
- (CommonAbstractSelector *)withSingleFieldFn:(id (^)(id))fn __attribute__((swift_name("withSingleField(fn:)")));
- (CommonAbstractSelector *)withSingleFieldByValueFn:(id (^)(id))fn __attribute__((swift_name("withSingleFieldByValue(fn:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ActionWithContext")))
@interface CommonActionWithContext : KotlinBase <CommonAction>
- (instancetype)initWithAction_:(id)action_ context_:(CommonReduksContext *)context_ __attribute__((swift_name("init(action_:context_:)"))) __attribute__((objc_designated_initializer));
@property (readonly) id action;
@property (readonly) CommonReduksContext *context;
@end;

__attribute__((swift_name("ActionWithContextPattern")))
@protocol CommonActionWithContextPattern
@required
- (BOOL)matchA:(CommonActionWithContext *)a __attribute__((swift_name("match(a:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ActionWithContextLambdaPattern")))
@interface CommonActionWithContextLambdaPattern : KotlinBase <CommonActionWithContextPattern>
- (instancetype)initWithMatch_ctx:(CommonReduksContext *)match_ctx matchfn:(CommonBoolean *(^)(id))matchfn __attribute__((swift_name("init(match_ctx:matchfn:)"))) __attribute__((objc_designated_initializer));
@property (readonly) CommonReduksContext *match_ctx;
@property (readonly) CommonBoolean *(^matchfn)(id);
@end;

__attribute__((swift_name("ReduksContext")))
@interface CommonReduksContext : KotlinBase
- (instancetype)initWithModuleId:(NSString *)moduleId modulePath:(NSArray<NSString *> * _Nullable)modulePath __attribute__((swift_name("init(moduleId:modulePath:)"))) __attribute__((objc_designated_initializer));
- (BOOL)isValid __attribute__((swift_name("isValid()")));
- (BOOL)hasEmptyPath __attribute__((swift_name("hasEmptyPath()")));
- (CommonReduksContext *)joinedWithOther:(CommonReduksContext *)other __attribute__((swift_name("joinedWith(other:)")));
- (CommonReduksContext *)divOther:(CommonReduksContext *)other __attribute__((swift_name("div(other:)")));
- (id)divAction:(id)action __attribute__((swift_name("div(action:)")));
@property (readonly) NSString *moduleId;
@property (readonly) NSArray<NSString *> * _Nullable modulePath;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReduksContext.Companion")))
@interface CommonReduksContextCompanion : KotlinBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (CommonReduksContext *)default __attribute__((swift_name("default()")));
- (CommonReduksContextTyped *)defaultTyped __attribute__((swift_name("defaultTyped()")));
- (NSString *)defaultModuleId __attribute__((swift_name("defaultModuleId()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReduksContextTyped")))
@interface CommonReduksContextTyped : CommonReduksContext
- (instancetype)initWithModuleId:(NSString *)moduleId modulePath:(NSArray<NSString *> * _Nullable)modulePath __attribute__((swift_name("init(moduleId:modulePath:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((swift_name("Actions")))
@interface CommonActions : KotlinBase <CommonAction>
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Actions.FetchingProfilesStartedAction")))
@interface CommonActionsFetchingProfilesStartedAction : KotlinBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Actions.FetchingProfilesSuccessAction")))
@interface CommonActionsFetchingProfilesSuccessAction : KotlinBase
- (instancetype)initWithProfiles:(NSArray<CommonProfile *> *)profiles __attribute__((swift_name("init(profiles:)"))) __attribute__((objc_designated_initializer));
@property (readonly) NSArray<CommonProfile *> *profiles;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Actions.FetchingProfilesFailedAction")))
@interface CommonActionsFetchingProfilesFailedAction : KotlinBase
- (instancetype)initWithMessage:(NSString *)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
@property (readonly) NSString *message;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Actions.NamePickedAction")))
@interface CommonActionsNamePickedAction : KotlinBase
- (instancetype)initWithName:(NSString *)name __attribute__((swift_name("init(name:)"))) __attribute__((objc_designated_initializer));
@property (readonly) NSString *name;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Actions.NextQuestionAction")))
@interface CommonActionsNextQuestionAction : KotlinBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Actions.GameCompleteAction")))
@interface CommonActionsGameCompleteAction : KotlinBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Actions.StartOverAction")))
@interface CommonActionsStartOverAction : KotlinBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Actions.ResetGameStateAction")))
@interface CommonActionsResetGameStateAction : KotlinBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Actions.StartQuestionTimerAction")))
@interface CommonActionsStartQuestionTimerAction : KotlinBase
- (instancetype)initWithInitialValue:(int32_t)initialValue __attribute__((swift_name("init(initialValue:)"))) __attribute__((objc_designated_initializer));
@property (readonly) int32_t initialValue;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Actions.DecrementCountDownAction")))
@interface CommonActionsDecrementCountDownAction : KotlinBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Actions.TimesUpAction")))
@interface CommonActionsTimesUpAction : KotlinBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Actions.SettingsTappedAction")))
@interface CommonActionsSettingsTappedAction : KotlinBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Actions.LoadAllSettingsAction")))
@interface CommonActionsLoadAllSettingsAction : KotlinBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Actions.ChangeNumQuestionsSettingsAction")))
@interface CommonActionsChangeNumQuestionsSettingsAction : KotlinBase
- (instancetype)initWithNum:(int32_t)num __attribute__((swift_name("init(num:)"))) __attribute__((objc_designated_initializer));
@property (readonly) int32_t num;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AppState")))
@interface CommonAppState : KotlinBase
- (instancetype)initWithIsLoadingProfiles:(BOOL)isLoadingProfiles profiles:(NSArray<CommonProfile *> *)profiles errorLoadingProfiles:(BOOL)errorLoadingProfiles errorMsg:(NSString *)errorMsg currentQuestionIndex:(int32_t)currentQuestionIndex waitingForNextQuestion:(BOOL)waitingForNextQuestion waitingForResultsTap:(BOOL)waitingForResultsTap questionClock:(int32_t)questionClock questions:(NSArray<CommonQuestion *> *)questions settings:(CommonUserSettings *)settings __attribute__((swift_name("init(isLoadingProfiles:profiles:errorLoadingProfiles:errorMsg:currentQuestionIndex:waitingForNextQuestion:waitingForResultsTap:questionClock:questions:settings:)"))) __attribute__((objc_designated_initializer));
- (CommonProfile *)profile:(CommonQuestion *)receiver __attribute__((swift_name("profile(_:)")));
- (CommonProfile * _Nullable)getProfileId:(id _Nullable)id __attribute__((swift_name("getProfile(id:)")));
- (CommonProfile *)currentQuestionProfile __attribute__((swift_name("currentQuestionProfile()")));
- (BOOL)isGameComplete __attribute__((swift_name("isGameComplete()")));
- (BOOL)component1 __attribute__((swift_name("component1()")));
- (NSArray<CommonProfile *> *)component2 __attribute__((swift_name("component2()")));
- (BOOL)component3 __attribute__((swift_name("component3()")));
- (NSString *)component4 __attribute__((swift_name("component4()")));
- (int32_t)component5 __attribute__((swift_name("component5()")));
- (BOOL)component6 __attribute__((swift_name("component6()")));
- (BOOL)component7 __attribute__((swift_name("component7()")));
- (int32_t)component8 __attribute__((swift_name("component8()")));
- (NSArray<CommonQuestion *> *)component9 __attribute__((swift_name("component9()")));
- (CommonUserSettings *)component10 __attribute__((swift_name("component10()")));
- (CommonAppState *)doCopyIsLoadingProfiles:(BOOL)isLoadingProfiles profiles:(NSArray<CommonProfile *> *)profiles errorLoadingProfiles:(BOOL)errorLoadingProfiles errorMsg:(NSString *)errorMsg currentQuestionIndex:(int32_t)currentQuestionIndex waitingForNextQuestion:(BOOL)waitingForNextQuestion waitingForResultsTap:(BOOL)waitingForResultsTap questionClock:(int32_t)questionClock questions:(NSArray<CommonQuestion *> *)questions settings:(CommonUserSettings *)settings __attribute__((swift_name("doCopy(isLoadingProfiles:profiles:errorLoadingProfiles:errorMsg:currentQuestionIndex:waitingForNextQuestion:waitingForResultsTap:questionClock:questions:settings:)")));
@property (readonly) NSString *timerText;
@property (readonly) CommonQuestion * _Nullable currentQuestion;
@property (readonly) int32_t numCorrect;
@property (readonly) BOOL isLoadingProfiles;
@property (readonly) NSArray<CommonProfile *> *profiles;
@property (readonly) BOOL errorLoadingProfiles;
@property (readonly) NSString *errorMsg;
@property (readonly) int32_t currentQuestionIndex;
@property (readonly) BOOL waitingForNextQuestion;
@property (readonly) BOOL waitingForResultsTap;
@property (readonly) int32_t questionClock;
@property (readonly) NSArray<CommonQuestion *> *questions;
@property (readonly) CommonUserSettings *settings;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AppState.Companion")))
@interface CommonAppStateCompanion : KotlinBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (readonly) CommonAppState *INITIAL_STATE;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Question")))
@interface CommonQuestion : KotlinBase
- (instancetype)initWithProfileId:(id)profileId choices:(NSArray<id> *)choices status:(CommonQuestionStatus *)status answerName:(NSString * _Nullable)answerName __attribute__((swift_name("init(profileId:choices:status:answerName:)"))) __attribute__((objc_designated_initializer));
- (id)component1 __attribute__((swift_name("component1()")));
- (NSArray<id> *)component2 __attribute__((swift_name("component2()")));
- (CommonQuestionStatus *)component3 __attribute__((swift_name("component3()")));
- (NSString * _Nullable)component4 __attribute__((swift_name("component4()")));
- (CommonQuestion *)doCopyProfileId:(id)profileId choices:(NSArray<id> *)choices status:(CommonQuestionStatus *)status answerName:(NSString * _Nullable)answerName __attribute__((swift_name("doCopy(profileId:choices:status:answerName:)")));
@property (readonly) id profileId;
@property (readonly) NSArray<id> *choices;
@property (readonly) CommonQuestionStatus *status;
@property (readonly) NSString * _Nullable answerName;
@end;

__attribute__((swift_name("KotlinComparable")))
@protocol CommonKotlinComparable
@required
- (int32_t)compareToOther:(id _Nullable)other __attribute__((swift_name("compareTo(other:)")));
@end;

__attribute__((swift_name("KotlinEnum")))
@interface CommonKotlinEnum : KotlinBase <CommonKotlinComparable>
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer));
- (int32_t)compareToOther:(CommonKotlinEnum *)other __attribute__((swift_name("compareTo(other:)")));
@property (readonly) NSString *name;
@property (readonly) int32_t ordinal;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Question.Status")))
@interface CommonQuestionStatus : CommonKotlinEnum
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
@property (class, readonly) CommonQuestionStatus *unanswered;
@property (class, readonly) CommonQuestionStatus *correct;
@property (class, readonly) CommonQuestionStatus *incorrect;
@property (class, readonly) CommonQuestionStatus *timesUp;
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (int32_t)compareToOther:(CommonQuestionStatus *)other __attribute__((swift_name("compareTo(other:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UserSettings")))
@interface CommonUserSettings : KotlinBase
- (instancetype)initWithNumQuestions:(int32_t)numQuestions __attribute__((swift_name("init(numQuestions:)"))) __attribute__((objc_designated_initializer));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (CommonUserSettings *)doCopyNumQuestions:(int32_t)numQuestions __attribute__((swift_name("doCopy(numQuestions:)")));
@property (readonly) int32_t numQuestions;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UserSettings.Companion")))
@interface CommonUserSettingsCompanion : KotlinBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (CommonUserSettings *)defaults __attribute__((swift_name("defaults()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GameEngine")))
@interface CommonGameEngine : KotlinBase
- (instancetype)initWithNavigator:(id<CommonNavigator>)navigator application:(id)application networkContext:(id<CommonKotlinCoroutineContext>)networkContext uiContext:(id<CommonKotlinCoroutineContext>)uiContext __attribute__((swift_name("init(navigator:application:networkContext:uiContext:)"))) __attribute__((objc_designated_initializer));
- (CommonPresenter *)attachViewView:(id<CommonView>)view __attribute__((swift_name("attachView(view:)")));
- (void)detachViewView:(id<CommonView>)view __attribute__((swift_name("detachView(view:)")));
@property (readonly) CommonVibrateUtil *vibrateUtil;
@property (readonly) id<CommonStore> appStore;
@end;

__attribute__((swift_name("Kotlinx_coroutines_core_nativeCoroutineScope")))
@protocol CommonKotlinx_coroutines_core_nativeCoroutineScope
@required
@property (readonly) id<CommonKotlinCoroutineContext> coroutineContext;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("NetworkThunks")))
@interface CommonNetworkThunks : KotlinBase <CommonKotlinx_coroutines_core_nativeCoroutineScope>
- (instancetype)initWithNetworkContext:(id<CommonKotlinCoroutineContext>)networkContext store:(id<CommonStore>)store timerThunks:(CommonTimerThunks *)timerThunks __attribute__((swift_name("init(networkContext:store:timerThunks:)"))) __attribute__((objc_designated_initializer));
- (CommonThunkImpl *)fetchProfiles __attribute__((swift_name("fetchProfiles()")));
@property (readonly) id<CommonStore> store;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TimerThunks")))
@interface CommonTimerThunks : KotlinBase <CommonKotlinx_coroutines_core_nativeCoroutineScope>
- (instancetype)initWithNetworkContext:(id<CommonKotlinCoroutineContext>)networkContext store:(id<CommonStore>)store __attribute__((swift_name("init(networkContext:store:)"))) __attribute__((objc_designated_initializer));
- (CommonThunkImpl *)startCountDownTimerInitialValue:(int32_t)initialValue __attribute__((swift_name("startCountDownTimer(initialValue:)")));
- (void)stopTimer __attribute__((swift_name("stopTimer()")));
@property (readonly) id<CommonStore> store;
@end;

__attribute__((swift_name("View")))
@protocol CommonView
@required
@end;

__attribute__((swift_name("Presenter")))
@interface CommonPresenter : KotlinBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (BOOL)isAttached __attribute__((swift_name("isAttached()")));
- (void)attachViewView:(id<CommonView> _Nullable)view __attribute__((swift_name("attachView(view:)")));
- (void)detachViewView:(id<CommonView> _Nullable)view __attribute__((swift_name("detachView(view:)")));
- (id<CommonStoreSubscriber>)makeSubscriber __attribute__((swift_name("makeSubscriber()")));
- (void)onStateChangeState:(CommonAppState *)state __attribute__((swift_name("onStateChange(state:)")));
@property id<CommonView> _Nullable view;
@property id<CommonStoreSubscriber> _Nullable subscriber;
@end;

__attribute__((swift_name("ViewEffect")))
@interface CommonViewEffect : KotlinBase
@end;

__attribute__((swift_name("ViewStates")))
@interface CommonViewStates : KotlinBase
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SettingsViewState")))
@interface CommonSettingsViewState : KotlinBase
- (instancetype)initWithNumQuestions:(int32_t)numQuestions __attribute__((swift_name("init(numQuestions:)"))) __attribute__((objc_designated_initializer));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (CommonSettingsViewState *)doCopyNumQuestions:(int32_t)numQuestions __attribute__((swift_name("doCopy(numQuestions:)")));
@property (readonly) int32_t numQuestions;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("QuestionViewState")))
@interface CommonQuestionViewState : KotlinBase
- (instancetype)initWithTitle:(NSString *)title profileImageUrl:(NSString *)profileImageUrl currentQuestion:(NSString *)currentQuestion numQuestions:(NSString *)numQuestions button1Text:(NSString *)button1Text button2Text:(NSString *)button2Text button3Text:(NSString *)button3Text button4Text:(NSString *)button4Text correctBtnNum:(int32_t)correctBtnNum timerText:(NSString *)timerText selectedBtnNum:(int32_t)selectedBtnNum __attribute__((swift_name("init(title:profileImageUrl:currentQuestion:numQuestions:button1Text:button2Text:button3Text:button4Text:correctBtnNum:timerText:selectedBtnNum:)"))) __attribute__((objc_designated_initializer));
@property (readonly) NSString *title;
@property (readonly) NSString *profileImageUrl;
@property (readonly) NSString *currentQuestion;
@property (readonly) NSString *numQuestions;
@property (readonly) NSString *button1Text;
@property (readonly) NSString *button2Text;
@property (readonly) NSString *button3Text;
@property (readonly) NSString *button4Text;
@property (readonly) int32_t correctBtnNum;
@property (readonly) NSString *timerText;
@property (readonly) int32_t selectedBtnNum;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GameResultsViewState")))
@interface CommonGameResultsViewState : KotlinBase
- (instancetype)initWithResultsText:(NSString *)resultsText messageText:(NSString *)messageText __attribute__((swift_name("init(resultsText:messageText:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (CommonGameResultsViewState *)doCopyResultsText:(NSString *)resultsText messageText:(NSString *)messageText __attribute__((swift_name("doCopy(resultsText:messageText:)")));
@property (readonly) NSString *resultsText;
@property (readonly) NSString *messageText;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PlatformLogger")))
@interface CommonPlatformLogger : KotlinBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)logDebugMsg:(NSString *)msg __attribute__((swift_name("logDebug(msg:)")));
- (void)logErrorMsg:(NSString *)msg __attribute__((swift_name("logError(msg:)")));
@property BOOL enabled;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Logger")))
@interface CommonLogger : KotlinBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)logger __attribute__((swift_name("init()")));
- (void)dMessage:(NSString *)message __attribute__((swift_name("d(message:)")));
- (void)eMessage:(NSString *)message exception:(CommonKotlinThrowable * _Nullable)exception __attribute__((swift_name("e(message:exception:)")));
@property BOOL enabled;
@end;

__attribute__((swift_name("KotlinCoroutineContext")))
@protocol CommonKotlinCoroutineContext
@required
- (id _Nullable)foldInitial:(id _Nullable)initial operation:(id _Nullable (^)(id _Nullable, id<CommonKotlinCoroutineContextElement>))operation __attribute__((swift_name("fold(initial:operation:)")));
- (id<CommonKotlinCoroutineContextElement> _Nullable)getKey:(id<CommonKotlinCoroutineContextKey>)key __attribute__((swift_name("get(key:)")));
- (id<CommonKotlinCoroutineContext>)minusKeyKey:(id<CommonKotlinCoroutineContextKey>)key __attribute__((swift_name("minusKey(key:)")));
- (id<CommonKotlinCoroutineContext>)plusContext:(id<CommonKotlinCoroutineContext>)context __attribute__((swift_name("plus(context:)")));
@end;

__attribute__((swift_name("KotlinCoroutineContextElement")))
@protocol CommonKotlinCoroutineContextElement <CommonKotlinCoroutineContext>
@required
@property (readonly) id<CommonKotlinCoroutineContextKey> key;
@end;

__attribute__((swift_name("KotlinAbstractCoroutineContextElement")))
@interface CommonKotlinAbstractCoroutineContextElement : KotlinBase <CommonKotlinCoroutineContextElement>
- (instancetype)initWithKey:(id<CommonKotlinCoroutineContextKey>)key __attribute__((swift_name("init(key:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((swift_name("KotlinContinuationInterceptor")))
@protocol CommonKotlinContinuationInterceptor <CommonKotlinCoroutineContextElement>
@required
- (id<CommonKotlinContinuation>)interceptContinuationContinuation:(id<CommonKotlinContinuation>)continuation __attribute__((swift_name("interceptContinuation(continuation:)")));
- (void)releaseInterceptedContinuationContinuation:(id<CommonKotlinContinuation>)continuation __attribute__((swift_name("releaseInterceptedContinuation(continuation:)")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_core_nativeCoroutineDispatcher")))
@interface CommonKotlinx_coroutines_core_nativeCoroutineDispatcher : CommonKotlinAbstractCoroutineContextElement <CommonKotlinContinuationInterceptor>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithKey:(id<CommonKotlinCoroutineContextKey>)key __attribute__((swift_name("init(key:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (void)dispatchContext:(id<CommonKotlinCoroutineContext>)context block:(id<CommonKotlinx_coroutines_core_nativeRunnable>)block __attribute__((swift_name("dispatch(context:block:)")));
- (void)dispatchYieldContext:(id<CommonKotlinCoroutineContext>)context block:(id<CommonKotlinx_coroutines_core_nativeRunnable>)block __attribute__((swift_name("dispatchYield(context:block:)")));
- (BOOL)isDispatchNeededContext:(id<CommonKotlinCoroutineContext>)context __attribute__((swift_name("isDispatchNeeded(context:)")));
- (CommonKotlinx_coroutines_core_nativeCoroutineDispatcher *)plusOther:(CommonKotlinx_coroutines_core_nativeCoroutineDispatcher *)other __attribute__((swift_name("plus(other:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PlatformDispatcher")))
@interface CommonPlatformDispatcher : CommonKotlinx_coroutines_core_nativeCoroutineDispatcher
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)platformDispatcher __attribute__((swift_name("init()")));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@end;

__attribute__((swift_name("Kotlinx_coroutines_core_nativeDelay")))
@protocol CommonKotlinx_coroutines_core_nativeDelay
@required
- (id<CommonKotlinx_coroutines_core_nativeDisposableHandle>)invokeOnTimeoutTimeMillis:(int64_t)timeMillis block:(id<CommonKotlinx_coroutines_core_nativeRunnable>)block __attribute__((swift_name("invokeOnTimeout(timeMillis:block:)")));
- (void)scheduleResumeAfterDelayTimeMillis:(int64_t)timeMillis continuation:(id<CommonKotlinx_coroutines_core_nativeCancellableContinuation>)continuation __attribute__((swift_name("scheduleResumeAfterDelay(timeMillis:continuation:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UI")))
@interface CommonUI : CommonKotlinx_coroutines_core_nativeCoroutineDispatcher <CommonKotlinx_coroutines_core_nativeDelay>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GameResultsPresenter")))
@interface CommonGameResultsPresenter : CommonPresenter
- (instancetype)initWithStore:(id<CommonStore>)store __attribute__((swift_name("init(store:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (void)startOverTapped __attribute__((swift_name("startOverTapped()")));
- (void)onBackPressed __attribute__((swift_name("onBackPressed()")));
- (void)attachViewView:(id<CommonGameResultsView>)view __attribute__((swift_name("attachView(view:)")));
- (void)detachViewView:(id<CommonGameResultsView>)view __attribute__((swift_name("detachView(view:)")));
@property (readonly) id<CommonStore> store;
@property id<CommonGameResultsView> _Nullable view;
@end;

__attribute__((swift_name("GameResultsView")))
@protocol CommonGameResultsView <CommonView>
@required
- (void)showResultsViewState:(CommonGameResultsViewState *)viewState __attribute__((swift_name("showResults(viewState:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("QuestionPresenter")))
@interface CommonQuestionPresenter : CommonPresenter
- (instancetype)initWithStore:(id<CommonStore>)store vibrateUtil:(CommonVibrateUtil *)vibrateUtil timerThunks:(CommonTimerThunks *)timerThunks __attribute__((swift_name("init(store:vibrateUtil:timerThunks:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (void)namePickedName:(NSString *)name __attribute__((swift_name("namePicked(name:)")));
- (void)nextTapped __attribute__((swift_name("nextTapped()")));
- (void)profileImageIsVisible __attribute__((swift_name("profileImageIsVisible()")));
- (void)endGameTapped __attribute__((swift_name("endGameTapped()")));
- (void)onBackPressed __attribute__((swift_name("onBackPressed()")));
- (void)attachViewView:(id<CommonQuestionView> _Nullable)view __attribute__((swift_name("attachView(view:)")));
- (void)detachViewView:(id<CommonQuestionView> _Nullable)view __attribute__((swift_name("detachView(view:)")));
@property (readonly) id<CommonStore> store;
@property id<CommonQuestionView> _Nullable view;
@end;

__attribute__((swift_name("QuestionView")))
@protocol CommonQuestionView <CommonView>
@required
- (void)showProfileViewState:(CommonQuestionViewState *)viewState __attribute__((swift_name("showProfile(viewState:)")));
- (void)showCorrectAnswerViewState:(CommonQuestionViewState *)viewState isEndGame:(BOOL)isEndGame __attribute__((swift_name("showCorrectAnswer(viewState:isEndGame:)")));
- (void)showWrongAnswerViewState:(CommonQuestionViewState *)viewState isEndGame:(BOOL)isEndGame __attribute__((swift_name("showWrongAnswer(viewState:isEndGame:)")));
- (void)setTimerTextViewState:(CommonQuestionViewState *)viewState __attribute__((swift_name("setTimerText(viewState:)")));
- (void)showTimesUpViewState:(CommonQuestionViewState *)viewState isEndGame:(BOOL)isEndGame __attribute__((swift_name("showTimesUp(viewState:isEndGame:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SettingsPresenter")))
@interface CommonSettingsPresenter : CommonPresenter
- (instancetype)initWithStore:(id<CommonStore>)store __attribute__((swift_name("init(store:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (void)numQuestionsChangedNumQuestions:(int32_t)numQuestions __attribute__((swift_name("numQuestionsChanged(numQuestions:)")));
- (void)attachViewView:(id<CommonSettingsView>)view __attribute__((swift_name("attachView(view:)")));
- (void)detachViewView:(id<CommonSettingsView>)view __attribute__((swift_name("detachView(view:)")));
@property (readonly) id<CommonStore> store;
@property id<CommonSettingsView> _Nullable view;
@end;

__attribute__((swift_name("SettingsView")))
@protocol CommonSettingsView <CommonView>
@required
- (void)showSettingsViewState:(CommonSettingsViewState *)viewState __attribute__((swift_name("showSettings(viewState:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("StartPresenter")))
@interface CommonStartPresenter : CommonPresenter
- (instancetype)initWithStore:(id<CommonStore>)store networkThunks:(CommonNetworkThunks *)networkThunks __attribute__((swift_name("init(store:networkThunks:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (void)startGame __attribute__((swift_name("startGame()")));
- (void)settingsTapped __attribute__((swift_name("settingsTapped()")));
- (void)attachViewView:(id<CommonStartView>)view __attribute__((swift_name("attachView(view:)")));
- (void)detachViewView:(id<CommonStartView>)view __attribute__((swift_name("detachView(view:)")));
@property (readonly) id<CommonStore> store;
@property id<CommonStartView> _Nullable view;
@end;

__attribute__((swift_name("StartView")))
@protocol CommonStartView <CommonView>
@required
- (void)showLoading __attribute__((swift_name("showLoading()")));
- (void)hideLoading __attribute__((swift_name("hideLoading()")));
- (void)showErrorMsg:(NSString *)msg __attribute__((swift_name("showError(msg:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TimeUtil")))
@interface CommonTimeUtil : KotlinBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)timeUtil __attribute__((swift_name("init()")));
- (int64_t)systemTimeMs __attribute__((swift_name("systemTimeMs()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("VibrateUtil")))
@interface CommonVibrateUtil : KotlinBase
- (instancetype)initWithApplication:(id)application __attribute__((swift_name("init(application:)"))) __attribute__((objc_designated_initializer));
- (void)vibrate __attribute__((swift_name("vibrate()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GatewayResponse")))
@interface CommonGatewayResponse : KotlinBase
- (instancetype)initWithResponse:(id _Nullable)response __attribute__((swift_name("init(response:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithResponse:(id _Nullable)response status:(int32_t)status message:(NSString *)message __attribute__((swift_name("init(response:status:message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithStatus:(int32_t)status errorResponse:(id _Nullable)errorResponse message:(NSString *)message __attribute__((swift_name("init(status:errorResponse:message:)"))) __attribute__((objc_designated_initializer));
@property (readonly) id _Nullable response;
@property (readonly) id _Nullable errorResponse;
@property (readonly) int32_t status;
@property (readonly) NSString * _Nullable message;
@property (readonly) BOOL isSuccessful;
@property (readonly) BOOL isFailure;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GatewayResponse.Companion")))
@interface CommonGatewayResponseCompanion : KotlinBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (CommonGatewayResponse *)createSuccessResponse:(id _Nullable)response status:(int32_t)status message:(NSString *)message __attribute__((swift_name("createSuccess(response:status:message:)")));
- (CommonGatewayResponse *)createErrorError:(id _Nullable)error status:(int32_t)status message:(NSString *)message __attribute__((swift_name("createError(error:status:message:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GenericError")))
@interface CommonGenericError : KotlinBase
- (instancetype)initWithMessage:(NSString *)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (CommonGenericError *)doCopyMessage:(NSString *)message __attribute__((swift_name("doCopy(message:)")));
@property (readonly) NSString *message;
@end;

__attribute__((swift_name("ProfilesRepository")))
@protocol CommonProfilesRepository
@required
@end;

__attribute__((swift_name("KtorProfilesRepository")))
@interface CommonKtorProfilesRepository : KotlinBase <CommonProfilesRepository>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ProfileListHolder")))
@interface CommonProfileListHolder : KotlinBase
- (instancetype)initWithProfiles:(NSArray<CommonProfile *> *)profiles __attribute__((swift_name("init(profiles:)"))) __attribute__((objc_designated_initializer));
@property (readonly) NSArray<CommonProfile *> *profiles;
@end;

__attribute__((swift_name("Kotlinx_serialization_runtime_nativeSerializationStrategy")))
@protocol CommonKotlinx_serialization_runtime_nativeSerializationStrategy
@required
- (void)serializeEncoder:(id<CommonKotlinx_serialization_runtime_nativeEncoder>)encoder obj:(id _Nullable)obj __attribute__((swift_name("serialize(encoder:obj:)")));
@property (readonly) id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor> descriptor;
@end;

__attribute__((swift_name("Kotlinx_serialization_runtime_nativeDeserializationStrategy")))
@protocol CommonKotlinx_serialization_runtime_nativeDeserializationStrategy
@required
- (id _Nullable)deserializeDecoder:(id<CommonKotlinx_serialization_runtime_nativeDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
- (id _Nullable)patchDecoder:(id<CommonKotlinx_serialization_runtime_nativeDecoder>)decoder old:(id _Nullable)old __attribute__((swift_name("patch(decoder:old:)")));
@property (readonly) id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor> descriptor;
@end;

__attribute__((swift_name("Kotlinx_serialization_runtime_nativeKSerializer")))
@protocol CommonKotlinx_serialization_runtime_nativeKSerializer <CommonKotlinx_serialization_runtime_nativeSerializationStrategy, CommonKotlinx_serialization_runtime_nativeDeserializationStrategy>
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ProfileListHolderSerializer")))
@interface CommonProfileListHolderSerializer : KotlinBase <CommonKotlinx_serialization_runtime_nativeKSerializer>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (CommonProfileListHolder *)deserializeDecoder:(id<CommonKotlinx_serialization_runtime_nativeDecoder>)input __attribute__((swift_name("deserialize(decoder:)")));
- (void)serializeEncoder:(id<CommonKotlinx_serialization_runtime_nativeEncoder>)encoder obj:(CommonProfileListHolder *)obj __attribute__((swift_name("serialize(encoder:obj:)")));
- (CommonProfileListHolder *)patchDecoder:(id<CommonKotlinx_serialization_runtime_nativeDecoder>)decoder old:(CommonProfileListHolder *)old __attribute__((swift_name("patch(decoder:old:)")));
@property (readonly) CommonKotlinx_serialization_runtime_nativeSerialClassDescImpl *descriptor;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("MockRepositoryFactory")))
@interface CommonMockRepositoryFactory : KotlinBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (id<CommonProfilesRepository>)success __attribute__((swift_name("success()")));
- (id<CommonProfilesRepository>)delayedLoadingDelayInMs:(int64_t)delayInMs __attribute__((swift_name("delayedLoading(delayInMs:)")));
- (id<CommonProfilesRepository>)error __attribute__((swift_name("error()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("MockRepositoryFactory.Companion")))
@interface CommonMockRepositoryFactoryCompanion : KotlinBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (NSArray<CommonProfile *> *)getValidResponse __attribute__((swift_name("getValidResponse()")));
@property (readonly) NSString *VALID_RESPONSE_JSON;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Profile")))
@interface CommonProfile : KotlinBase
- (instancetype)initWithId:(NSString *)id type:(NSString *)type slug:(NSString *)slug jobTitle:(NSString * _Nullable)jobTitle firstName:(NSString *)firstName lastName:(NSString *)lastName headshot:(CommonHeadshot *)headshot socialLinks:(NSArray<CommonSocialLinks *> * _Nullable)socialLinks __attribute__((swift_name("init(id:type:slug:jobTitle:firstName:lastName:headshot:socialLinks:)"))) __attribute__((objc_designated_initializer));
- (BOOL)matchesName:(NSString *)name __attribute__((swift_name("matches(name:)")));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (NSString * _Nullable)component4 __attribute__((swift_name("component4()")));
- (NSString *)component5 __attribute__((swift_name("component5()")));
- (NSString *)component6 __attribute__((swift_name("component6()")));
- (CommonHeadshot *)component7 __attribute__((swift_name("component7()")));
- (NSArray<CommonSocialLinks *> * _Nullable)component8 __attribute__((swift_name("component8()")));
- (CommonProfile *)doCopyId:(NSString *)id type:(NSString *)type slug:(NSString *)slug jobTitle:(NSString * _Nullable)jobTitle firstName:(NSString *)firstName lastName:(NSString *)lastName headshot:(CommonHeadshot *)headshot socialLinks:(NSArray<CommonSocialLinks *> * _Nullable)socialLinks __attribute__((swift_name("doCopy(id:type:slug:jobTitle:firstName:lastName:headshot:socialLinks:)")));
@property (readonly) NSString *id;
@property (readonly) NSString *type;
@property (readonly) NSString *slug;
@property (readonly) NSString * _Nullable jobTitle;
@property (readonly) NSString *firstName;
@property (readonly) NSString *lastName;
@property (readonly) CommonHeadshot *headshot;
@property (readonly) NSArray<CommonSocialLinks *> * _Nullable socialLinks;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Profile.Companion")))
@interface CommonProfileCompanion : KotlinBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<CommonKotlinx_serialization_runtime_nativeKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((swift_name("Kotlinx_serialization_runtime_nativeGeneratedSerializer")))
@protocol CommonKotlinx_serialization_runtime_nativeGeneratedSerializer <CommonKotlinx_serialization_runtime_nativeKSerializer>
@required
- (CommonKotlinArray *)childSerializers __attribute__((swift_name("childSerializers()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Profile.$serializer")))
@interface CommonProfile$serializer : KotlinBase <CommonKotlinx_serialization_runtime_nativeGeneratedSerializer>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)$serializer __attribute__((swift_name("init()")));
- (CommonProfile *)deserializeDecoder:(id<CommonKotlinx_serialization_runtime_nativeDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
- (CommonProfile *)patchDecoder:(id<CommonKotlinx_serialization_runtime_nativeDecoder>)decoder old:(CommonProfile *)old __attribute__((swift_name("patch(decoder:old:)")));
- (void)serializeEncoder:(id<CommonKotlinx_serialization_runtime_nativeEncoder>)encoder obj:(CommonProfile *)obj __attribute__((swift_name("serialize(encoder:obj:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Headshot")))
@interface CommonHeadshot : KotlinBase
- (instancetype)initWithType:(NSString *)type id:(NSString *)id url:(NSString * _Nullable)url height:(NSString * _Nullable)height width:(NSString * _Nullable)width __attribute__((swift_name("init(type:id:url:height:width:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString * _Nullable)component3 __attribute__((swift_name("component3()")));
- (NSString * _Nullable)component4 __attribute__((swift_name("component4()")));
- (NSString * _Nullable)component5 __attribute__((swift_name("component5()")));
- (CommonHeadshot *)doCopyType:(NSString *)type id:(NSString *)id url:(NSString * _Nullable)url height:(NSString * _Nullable)height width:(NSString * _Nullable)width __attribute__((swift_name("doCopy(type:id:url:height:width:)")));
@property (readonly) NSString *type;
@property (readonly) NSString *id;
@property (readonly) NSString * _Nullable url;
@property (readonly) NSString * _Nullable height;
@property (readonly) NSString * _Nullable width;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Headshot.Companion")))
@interface CommonHeadshotCompanion : KotlinBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<CommonKotlinx_serialization_runtime_nativeKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Headshot.$serializer")))
@interface CommonHeadshot$serializer : KotlinBase <CommonKotlinx_serialization_runtime_nativeGeneratedSerializer>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)$serializer __attribute__((swift_name("init()")));
- (CommonHeadshot *)deserializeDecoder:(id<CommonKotlinx_serialization_runtime_nativeDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
- (CommonHeadshot *)patchDecoder:(id<CommonKotlinx_serialization_runtime_nativeDecoder>)decoder old:(CommonHeadshot *)old __attribute__((swift_name("patch(decoder:old:)")));
- (void)serializeEncoder:(id<CommonKotlinx_serialization_runtime_nativeEncoder>)encoder obj:(CommonHeadshot *)obj __attribute__((swift_name("serialize(encoder:obj:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SocialLinks")))
@interface CommonSocialLinks : KotlinBase
- (instancetype)initWithType:(NSString *)type callToAction:(NSString *)callToAction url:(NSString * _Nullable)url __attribute__((swift_name("init(type:callToAction:url:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString * _Nullable)component3 __attribute__((swift_name("component3()")));
- (CommonSocialLinks *)doCopyType:(NSString *)type callToAction:(NSString *)callToAction url:(NSString * _Nullable)url __attribute__((swift_name("doCopy(type:callToAction:url:)")));
@property (readonly) NSString *type;
@property (readonly) NSString *callToAction;
@property (readonly) NSString * _Nullable url;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SocialLinks.Companion")))
@interface CommonSocialLinksCompanion : KotlinBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<CommonKotlinx_serialization_runtime_nativeKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SocialLinks.$serializer")))
@interface CommonSocialLinks$serializer : KotlinBase <CommonKotlinx_serialization_runtime_nativeGeneratedSerializer>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)$serializer __attribute__((swift_name("init()")));
- (CommonSocialLinks *)deserializeDecoder:(id<CommonKotlinx_serialization_runtime_nativeDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
- (CommonSocialLinks *)patchDecoder:(id<CommonKotlinx_serialization_runtime_nativeDecoder>)decoder old:(CommonSocialLinks *)old __attribute__((swift_name("patch(decoder:old:)")));
- (void)serializeEncoder:(id<CommonKotlinx_serialization_runtime_nativeEncoder>)encoder obj:(CommonSocialLinks *)obj __attribute__((swift_name("serialize(encoder:obj:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("LocalStorageSettingsRepository")))
@interface CommonLocalStorageSettingsRepository : KotlinBase
- (instancetype)initWithSettings:(id<CommonMultiplatform_settingsSettings>)settings __attribute__((swift_name("init(settings:)"))) __attribute__((objc_designated_initializer));
- (void)saveNumRoundsNumRounds:(int32_t)numRounds __attribute__((swift_name("saveNumRounds(numRounds:)")));
- (int32_t)loadNumRounds __attribute__((swift_name("loadNumRounds()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("LocalStorageSettingsRepository.Companion")))
@interface CommonLocalStorageSettingsRepositoryCompanion : KotlinBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (readonly) NSString *NUM_ROUNDS;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("NavigationMiddleware")))
@interface CommonNavigationMiddleware : KotlinBase
- (instancetype)initWithNavigator:(id<CommonNavigator>)navigator __attribute__((swift_name("init(navigator:)"))) __attribute__((objc_designated_initializer));
- (id)dispatchStore:(id<CommonStore>)store nextDispatcher:(id (^)(id))nextDispatcher action:(id)action __attribute__((swift_name("dispatch(store:nextDispatcher:action:)")));
@property (readonly) id<CommonNavigator> navigator;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Screen")))
@interface CommonScreen : CommonKotlinEnum
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
@property (class, readonly) CommonScreen *start;
@property (class, readonly) CommonScreen *question;
@property (class, readonly) CommonScreen *gameComplete;
@property (class, readonly) CommonScreen *settings;
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (int32_t)compareToOther:(CommonScreen *)other __attribute__((swift_name("compareTo(other:)")));
@end;

__attribute__((swift_name("Navigator")))
@protocol CommonNavigator
@required
- (void)gotoScreen:(CommonScreen *)screen __attribute__((swift_name("goto(screen:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SettingsMiddleware")))
@interface CommonSettingsMiddleware : KotlinBase
- (instancetype)initWithSettings:(CommonLocalStorageSettingsRepository *)settings __attribute__((swift_name("init(settings:)"))) __attribute__((objc_designated_initializer));
- (id)dispatchStore:(id<CommonStore>)store nextDispatcher:(id (^)(id))nextDispatcher action:(id)action __attribute__((swift_name("dispatch(store:nextDispatcher:action:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ViewEffectsMiddleware")))
@interface CommonViewEffectsMiddleware : KotlinBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)subscribeToViewEffectsSubscriber:(CommonKotlinUnit *(^)(CommonViewEffect *))subscriber __attribute__((swift_name("subscribeToViewEffects(subscriber:)")));
- (void)unsubscribeSubscriber:(CommonKotlinUnit *(^)(CommonViewEffect *))subscriber __attribute__((swift_name("unsubscribe(subscriber:)")));
- (id)dispatchStore:(id<CommonStore>)store nextDispatcher:(id (^)(id))nextDispatcher action:(id)action __attribute__((swift_name("dispatch(store:nextDispatcher:action:)")));
@end;

@interface CommonReduksContext (Extensions)
- (CommonActionWithContextLambdaPattern *)matchAMatchfn:(CommonBoolean *(^)(id))matchfn __attribute__((swift_name("matchA(matchfn:)")));
- (CommonActionWithContextLambdaPattern *)isA __attribute__((swift_name("isA()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinArray")))
@interface CommonKotlinArray : KotlinBase
+ (instancetype)arrayWithSize:(int32_t)size init:(id _Nullable (^)(CommonInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (id _Nullable)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (id<CommonKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(id _Nullable)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size;
@end;

@interface CommonKotlinArray (Extensions)
- (id<CommonStoreEnhancer>)toEnhancer __attribute__((swift_name("toEnhancer()")));
@end;

@interface CommonAppState (Extensions)
- (CommonQuestionViewState *)toQuestionViewState __attribute__((swift_name("toQuestionViewState()")));
- (CommonGameResultsViewState *)toGameResultsViewState __attribute__((swift_name("toGameResultsViewState()")));
@end;

@interface CommonProfile (Extensions)
- (NSString *)displayName __attribute__((swift_name("displayName()")));
@end;

@interface CommonUserSettings (Extensions)
- (CommonSettingsViewState *)toViewState __attribute__((swift_name("toViewState()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReduksKt")))
@interface CommonReduksKt : KotlinBase
+ (id _Nullable)state:(id<CommonReduks>)receiver __attribute__((swift_name("state(_:)")));
+ (id)dispatch:(id<CommonReduks>)receiver action:(id)action __attribute__((swift_name("dispatch(_:action:)")));
+ (void)subscribe:(id<CommonReduks>)receiver subscriptionTag:(NSString *)subscriptionTag storeSubscriber:(id<CommonStoreSubscriber>)storeSubscriber __attribute__((swift_name("subscribe(_:subscriptionTag:storeSubscriber:)")));
+ (void)subscribe:(id<CommonReduks>)receiver subscriptionTag:(NSString *)subscriptionTag storeSubscriberBuilder:(id<CommonStoreSubscriberBuilder>)storeSubscriberBuilder __attribute__((swift_name("subscribe(_:subscriptionTag:storeSubscriberBuilder:)")));
+ (void)unsubscribe:(id<CommonReduks>)receiver subscriptionTag:(NSString *)subscriptionTag __attribute__((swift_name("unsubscribe(_:subscriptionTag:)")));
+ (void)unsubscribeAll:(id<CommonReduks>)receiver __attribute__((swift_name("unsubscribeAll(_:)")));
+ (CommonDispatcherFn *)getDispatcherFn:(id<CommonReduks>)receiver __attribute__((swift_name("getDispatcherFn(_:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("StoreKt")))
@interface CommonStoreKt : KotlinBase
+ (id<CommonStoreSubscription>)subscribe:(id<CommonStore>)receiver lambda:(CommonKotlinUnit *(^)(void))lambda __attribute__((swift_name("subscribe(_:lambda:)")));
+ (id<CommonStoreSubscription> _Nullable)subscribe:(id<CommonStore>)receiver sb:(id<CommonStoreSubscriberBuilder> _Nullable)sb __attribute__((swift_name("subscribe(_:sb:)")));
+ (CommonDispatcherFn *)getDispatcherFn:(id<CommonStore>)receiver __attribute__((swift_name("getDispatcherFn(_:)")));
+ (id _Nullable)invoke:(id (^ _Nullable)(id))receiver action:(id)action __attribute__((swift_name("invoke(_:action:)")));
+ (id)dispatch_a:(id<CommonStore>)receiver action:(id<CommonAction>)action __attribute__((swift_name("dispatch_a(_:action:)")));
+ (id)dispatch_sa:(id<CommonStore>)receiver action:(id<CommonStandardAction>)action __attribute__((swift_name("dispatch_sa(_:action:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("StoreSubscriptionKt")))
@interface CommonStoreSubscriptionKt : KotlinBase
+ (void)unsubscribe:(NSMutableArray<id<CommonStoreSubscription>> *)receiver __attribute__((swift_name("unsubscribe(_:)")));
+ (void)addToList:(id<CommonStoreSubscription>)receiver subscriptions:(NSMutableArray<id<CommonStoreSubscription>> *)subscriptions __attribute__((swift_name("addToList(_:subscriptions:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("StoreCreatorKt")))
@interface CommonStoreCreatorKt : KotlinBase
+ (id<CommonStoreCreator>)enhancedWith:(id<CommonStoreCreator>)receiver enhancers:(CommonKotlinArray *)enhancers __attribute__((swift_name("enhancedWith(_:enhancers:)")));
+ (id<CommonStoreCreator>)withMiddlewares:(id<CommonStoreCreator>)receiver middlewares:(CommonKotlinArray *)middlewares __attribute__((swift_name("withMiddlewares(_:middlewares:)")));
+ (id<CommonStore>)create:(id<CommonStoreCreator>)receiver reducer:(id _Nullable (^)(id _Nullable, id))reducer initialState:(id _Nullable)initialState enhancer:(id<CommonStoreEnhancer>)enhancer __attribute__((swift_name("create(_:reducer:initialState:enhancer:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BuildersKt")))
@interface CommonBuildersKt : KotlinBase
+ (id (^)(id<CommonStore>, id (^)(id), id))MiddlewareFnMiddlewareFn:(id (^)(id<CommonStore>, id (^)(id), id))middlewareFn __attribute__((swift_name("MiddlewareFn(middlewareFn:)")));
+ (id _Nullable (^)(id _Nullable, id))ReducerFnReducerFn:(id _Nullable (^)(id _Nullable, id))reducerFn __attribute__((swift_name("ReducerFn(reducerFn:)")));
+ (CommonStoreEnhancerImpl *)StoreEnhancerFnStoreEnhancerFn:(id<CommonStoreCreator> (^)(id<CommonStoreCreator>))storeEnhancerFn __attribute__((swift_name("StoreEnhancerFn(storeEnhancerFn:)")));
+ (CommonStoreSubscriberImpl *)StoreSubscriberFnSubscriberFn:(CommonKotlinUnit *(^)(void))subscriberFn __attribute__((swift_name("StoreSubscriberFn(subscriberFn:)")));
+ (CommonStoreSubscriberBuilderImpl *)StoreSubscriberBuilderFnStoreSubscriberBuilderFn:(id<CommonStoreSubscriber> (^)(id<CommonStore>))storeSubscriberBuilderFn __attribute__((swift_name("StoreSubscriberBuilderFn(storeSubscriberBuilderFn:)")));
+ (CommonStoreSubscriberBuilderImpl2 *)StoreSubscriberBuilderFnStoreSubscriberBuilderFn2:(id<CommonStoreSubscriber> (^)(id<CommonStore>, CommonSelectorBuilder *))storeSubscriberBuilderFn2 __attribute__((swift_name("StoreSubscriberBuilderFn(storeSubscriberBuilderFn2:)")));
+ (id<CommonStoreSubscriber>)SelectorSubscriberFnStore:(id<CommonStore>)store selectorSubscriberBuilderInit:(CommonKotlinUnit *(^)(CommonSelectorSubscriberBuilder *))selectorSubscriberBuilderInit __attribute__((swift_name("SelectorSubscriberFn(store:selectorSubscriberBuilderInit:)")));
+ (CommonThunkImpl *)ThunkFnThunkFn:(id (^)(id (^)(id), id _Nullable))thunkFn __attribute__((swift_name("ThunkFn(thunkFn:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CombineEnhancersKt")))
@interface CommonCombineEnhancersKt : KotlinBase
+ (CommonStoreEnhancerImpl *)combineEnhancersEnhancers:(CommonKotlinArray *)enhancers __attribute__((swift_name("combineEnhancers(enhancers:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CombineReducersKt")))
@interface CommonCombineReducersKt : KotlinBase
+ (id _Nullable (^)(id _Nullable, id))combineReducersReducers:(CommonKotlinArray *)reducers __attribute__((swift_name("combineReducers(reducers:)")));
+ (id _Nullable (^)(id _Nullable, id))combinedWith:(id _Nullable (^)(id _Nullable, id))receiver reducers:(CommonKotlinArray *)reducers __attribute__((swift_name("combinedWith(_:reducers:)")));
+ (id _Nullable (^)(id _Nullable, id))plus:(id _Nullable (^)(id _Nullable, id))receiver other:(id _Nullable (^)(id _Nullable, id))other __attribute__((swift_name("plus(_:other:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReselectKt")))
@interface CommonReselectKt : KotlinBase
+ (id<CommonMemoizer>)computationMemoizerComputeFn:(id _Nullable (^)(CommonKotlinArray *))computeFn __attribute__((swift_name("computationMemoizer(computeFn:)")));
+ (id<CommonMemoizer>)singleInputMemoizerFunc:(id _Nullable (^)(CommonKotlinArray *))func __attribute__((swift_name("singleInputMemoizer(func:)")));
+ (void)whenChangeOf:(id _Nullable)receiver selector:(id<CommonSelector>)selector blockfn:(CommonKotlinUnit *(^)(id _Nullable))blockfn __attribute__((swift_name("whenChangeOf(_:selector:blockfn:)")));
@property (class, readonly) CommonBoolean *(^byRefEqualityCheck)(id, id);
@property (class, readonly) CommonBoolean *(^byValEqualityCheck)(id, id);
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ThunkMiddlewareKt")))
@interface CommonThunkMiddlewareKt : KotlinBase
+ (id)thunkMiddlewareStore:(id<CommonStore>)store nextDispatcher:(id (^)(id))nextDispatcher action:(id)action __attribute__((swift_name("thunkMiddleware(store:nextDispatcher:action:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ApplyMiddlewareKt")))
@interface CommonApplyMiddlewareKt : KotlinBase
+ (id<CommonStore>)applyMiddleware:(id<CommonStore>)receiver middlewares:(CommonKotlinArray *)middlewares __attribute__((swift_name("applyMiddleware(_:middlewares:)")));
+ (id<CommonStoreEnhancer>)toEnhancer:(id (^)(id<CommonStore>, id (^)(id), id))receiver __attribute__((swift_name("toEnhancer(_:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReducersKt")))
@interface CommonReducersKt : KotlinBase
+ (CommonAppState *)reducerState:(CommonAppState *)state action:(id)action __attribute__((swift_name("reducer(state:action:)")));
+ (NSArray<CommonQuestion *> *)generateRoundsProfiles:(NSArray<CommonProfile *> *)profiles n:(int32_t)n __attribute__((swift_name("generateRounds(profiles:n:)")));
+ (NSArray<id> *)takeRandomDistinct:(NSArray<id> *)receiver n:(int32_t)n __attribute__((swift_name("takeRandomDistinct(_:n:)")));
+ (id _Nullable)takeRandom:(NSArray<id> *)receiver __attribute__((swift_name("takeRandom(_:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ViewStatesKt")))
@interface CommonViewStatesKt : KotlinBase
@property (class, readonly) NSArray<NSString *> *perfectScoreResponses;
@property (class, readonly) NSArray<NSString *> *goodScoreResponses;
@property (class, readonly) NSArray<NSString *> *okScoreResponses;
@property (class, readonly) NSArray<NSString *> *badScoreResponses;
@property (class, readonly) NSArray<NSString *> *zeroScoreResponses;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DebounceKt")))
@interface CommonDebounceKt : KotlinBase
+ (CommonKotlinUnit *(^)(void))debounceDelay:(int64_t)delay f:(CommonKotlinUnit *(^)(void))f __attribute__((swift_name("debounce(delay:f:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GatewayResponseKt")))
@interface CommonGatewayResponseKt : KotlinBase
+ (CommonGatewayResponse *)retrySuccessOrThrowNumRetries:(int32_t)numRetries retryWaitInMs:(int64_t)retryWaitInMs ex:(CommonKotlinException *)ex f:(CommonGatewayResponse *(^)(void))f __attribute__((swift_name("retrySuccessOrThrow(numRetries:retryWaitInMs:ex:f:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UserSettingsKt")))
@interface CommonUserSettingsKt : KotlinBase
+ (id<CommonMultiplatform_settingsSettings>)userSettingsContext:(id _Nullable)context __attribute__((swift_name("userSettings(context:)")));
@end;

__attribute__((swift_name("KotlinThrowable")))
@interface CommonKotlinThrowable : KotlinBase
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(CommonKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(CommonKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (CommonKotlinArray *)getStackTrace __attribute__((swift_name("getStackTrace()")));
- (void)printStackTrace __attribute__((swift_name("printStackTrace()")));
@property (readonly) CommonKotlinThrowable * _Nullable cause;
@property (readonly) NSString * _Nullable message;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinUnit")))
@interface CommonKotlinUnit : KotlinBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)unit __attribute__((swift_name("init()")));
@end;

__attribute__((swift_name("KotlinCoroutineContextKey")))
@protocol CommonKotlinCoroutineContextKey
@required
@end;

__attribute__((swift_name("KotlinContinuation")))
@protocol CommonKotlinContinuation
@required
- (void)resumeWithResult:(id _Nullable)result __attribute__((swift_name("resumeWith(result:)")));
@property (readonly) id<CommonKotlinCoroutineContext> context;
@end;

__attribute__((swift_name("Kotlinx_coroutines_core_nativeRunnable")))
@protocol CommonKotlinx_coroutines_core_nativeRunnable
@required
- (void)run __attribute__((swift_name("run()")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_core_nativeDisposableHandle")))
@protocol CommonKotlinx_coroutines_core_nativeDisposableHandle
@required
- (void)dispose __attribute__((swift_name("dispose()")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_core_nativeCancellableContinuation")))
@protocol CommonKotlinx_coroutines_core_nativeCancellableContinuation <CommonKotlinContinuation>
@required
- (BOOL)cancelCause:(CommonKotlinThrowable * _Nullable)cause __attribute__((swift_name("cancel(cause:)")));
- (void)completeResumeToken:(id)token __attribute__((swift_name("completeResume(token:)")));
- (void)doInitCancellability __attribute__((swift_name("doInitCancellability()")));
- (void)invokeOnCancellationHandler:(CommonKotlinUnit *(^)(CommonKotlinThrowable * _Nullable))handler __attribute__((swift_name("invokeOnCancellation(handler:)")));
- (id _Nullable)tryResumeValue:(id _Nullable)value idempotent:(id _Nullable)idempotent __attribute__((swift_name("tryResume(value:idempotent:)")));
- (id _Nullable)tryResumeWithExceptionException:(CommonKotlinThrowable *)exception __attribute__((swift_name("tryResumeWithException(exception:)")));
- (void)resumeUndispatched:(CommonKotlinx_coroutines_core_nativeCoroutineDispatcher *)receiver value:(id _Nullable)value __attribute__((swift_name("resumeUndispatched(_:value:)")));
- (void)resumeUndispatchedWithException:(CommonKotlinx_coroutines_core_nativeCoroutineDispatcher *)receiver exception:(CommonKotlinThrowable *)exception __attribute__((swift_name("resumeUndispatchedWithException(_:exception:)")));
@property (readonly) BOOL isActive;
@property (readonly) BOOL isCancelled;
@property (readonly) BOOL isCompleted;
@end;

__attribute__((swift_name("Kotlinx_serialization_runtime_nativeEncoder")))
@protocol CommonKotlinx_serialization_runtime_nativeEncoder
@required
- (id<CommonKotlinx_serialization_runtime_nativeCompositeEncoder>)beginCollectionDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc collectionSize:(int32_t)collectionSize typeParams:(CommonKotlinArray *)typeParams __attribute__((swift_name("beginCollection(desc:collectionSize:typeParams:)")));
- (id<CommonKotlinx_serialization_runtime_nativeCompositeEncoder>)beginStructureDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc typeParams:(CommonKotlinArray *)typeParams __attribute__((swift_name("beginStructure(desc:typeParams:)")));
- (void)encodeBooleanValue:(BOOL)value __attribute__((swift_name("encodeBoolean(value:)")));
- (void)encodeByteValue:(int8_t)value __attribute__((swift_name("encodeByte(value:)")));
- (void)encodeCharValue:(unichar)value __attribute__((swift_name("encodeChar(value:)")));
- (void)encodeDoubleValue:(double)value __attribute__((swift_name("encodeDouble(value:)")));
- (void)encodeEnumEnumDescription:(CommonKotlinx_serialization_runtime_nativeEnumDescriptor *)enumDescription ordinal:(int32_t)ordinal __attribute__((swift_name("encodeEnum(enumDescription:ordinal:)")));
- (void)encodeFloatValue:(float)value __attribute__((swift_name("encodeFloat(value:)")));
- (void)encodeIntValue:(int32_t)value __attribute__((swift_name("encodeInt(value:)")));
- (void)encodeLongValue:(int64_t)value __attribute__((swift_name("encodeLong(value:)")));
- (void)encodeNotNullMark __attribute__((swift_name("encodeNotNullMark()")));
- (void)encodeNull __attribute__((swift_name("encodeNull()")));
- (void)encodeNullableSerializableValueSerializer:(id<CommonKotlinx_serialization_runtime_nativeSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeNullableSerializableValue(serializer:value:)")));
- (void)encodeSerializableValueSerializer:(id<CommonKotlinx_serialization_runtime_nativeSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeSerializableValue(serializer:value:)")));
- (void)encodeShortValue:(int16_t)value __attribute__((swift_name("encodeShort(value:)")));
- (void)encodeStringValue:(NSString *)value __attribute__((swift_name("encodeString(value:)")));
- (void)encodeUnit __attribute__((swift_name("encodeUnit()")));
@property (readonly) id<CommonKotlinx_serialization_runtime_nativeSerialContext> context;
@end;

__attribute__((swift_name("Kotlinx_serialization_runtime_nativeSerialDescriptor")))
@protocol CommonKotlinx_serialization_runtime_nativeSerialDescriptor
@required
- (NSArray<id<CommonKotlinAnnotation>> *)getElementAnnotationsIndex:(int32_t)index __attribute__((swift_name("getElementAnnotations(index:)")));
- (id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)getElementDescriptorIndex:(int32_t)index __attribute__((swift_name("getElementDescriptor(index:)")));
- (int32_t)getElementIndexName:(NSString *)name __attribute__((swift_name("getElementIndex(name:)")));
- (NSString *)getElementNameIndex:(int32_t)index __attribute__((swift_name("getElementName(index:)")));
- (NSArray<id<CommonKotlinAnnotation>> *)getEntityAnnotations __attribute__((swift_name("getEntityAnnotations()")));
- (BOOL)isElementOptionalIndex:(int32_t)index __attribute__((swift_name("isElementOptional(index:)")));
@property (readonly) int32_t elementsCount;
@property (readonly) BOOL isNullable;
@property (readonly) CommonKotlinx_serialization_runtime_nativeSerialKind *kind;
@property (readonly) NSString *name;
@end;

__attribute__((swift_name("Kotlinx_serialization_runtime_nativeDecoder")))
@protocol CommonKotlinx_serialization_runtime_nativeDecoder
@required
- (id<CommonKotlinx_serialization_runtime_nativeCompositeDecoder>)beginStructureDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc typeParams:(CommonKotlinArray *)typeParams __attribute__((swift_name("beginStructure(desc:typeParams:)")));
- (BOOL)decodeBoolean __attribute__((swift_name("decodeBoolean()")));
- (int8_t)decodeByte __attribute__((swift_name("decodeByte()")));
- (unichar)decodeChar __attribute__((swift_name("decodeChar()")));
- (double)decodeDouble __attribute__((swift_name("decodeDouble()")));
- (int32_t)decodeEnumEnumDescription:(CommonKotlinx_serialization_runtime_nativeEnumDescriptor *)enumDescription __attribute__((swift_name("decodeEnum(enumDescription:)")));
- (float)decodeFloat __attribute__((swift_name("decodeFloat()")));
- (int32_t)decodeInt __attribute__((swift_name("decodeInt()")));
- (int64_t)decodeLong __attribute__((swift_name("decodeLong()")));
- (BOOL)decodeNotNullMark __attribute__((swift_name("decodeNotNullMark()")));
- (CommonKotlinNothing * _Nullable)decodeNull __attribute__((swift_name("decodeNull()")));
- (id _Nullable)decodeNullableSerializableValueDeserializer:(id<CommonKotlinx_serialization_runtime_nativeDeserializationStrategy>)deserializer __attribute__((swift_name("decodeNullableSerializableValue(deserializer:)")));
- (id _Nullable)decodeSerializableValueDeserializer:(id<CommonKotlinx_serialization_runtime_nativeDeserializationStrategy>)deserializer __attribute__((swift_name("decodeSerializableValue(deserializer:)")));
- (int16_t)decodeShort __attribute__((swift_name("decodeShort()")));
- (NSString *)decodeString __attribute__((swift_name("decodeString()")));
- (void)decodeUnit __attribute__((swift_name("decodeUnit()")));
- (id _Nullable)updateNullableSerializableValueDeserializer:(id<CommonKotlinx_serialization_runtime_nativeDeserializationStrategy>)deserializer old:(id _Nullable)old __attribute__((swift_name("updateNullableSerializableValue(deserializer:old:)")));
- (id _Nullable)updateSerializableValueDeserializer:(id<CommonKotlinx_serialization_runtime_nativeDeserializationStrategy>)deserializer old:(id _Nullable)old __attribute__((swift_name("updateSerializableValue(deserializer:old:)")));
@property (readonly) id<CommonKotlinx_serialization_runtime_nativeSerialContext> context;
@property (readonly) CommonKotlinx_serialization_runtime_nativeUpdateMode *updateMode;
@end;

__attribute__((swift_name("Kotlinx_serialization_runtime_nativeSerialClassDescImpl")))
@interface CommonKotlinx_serialization_runtime_nativeSerialClassDescImpl : KotlinBase <CommonKotlinx_serialization_runtime_nativeSerialDescriptor>
- (instancetype)initWithName:(NSString *)name generatedSerializer:(id<CommonKotlinx_serialization_runtime_nativeGeneratedSerializer> _Nullable)generatedSerializer __attribute__((swift_name("init(name:generatedSerializer:)"))) __attribute__((objc_designated_initializer));
- (void)addElementName:(NSString *)name isOptional:(BOOL)isOptional __attribute__((swift_name("addElement(name:isOptional:)")));
- (void)pushAnnotationA:(id<CommonKotlinAnnotation>)a __attribute__((swift_name("pushAnnotation(a:)")));
- (void)pushClassAnnotationA:(id<CommonKotlinAnnotation>)a __attribute__((swift_name("pushClassAnnotation(a:)")));
- (void)pushDescriptorDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc __attribute__((swift_name("pushDescriptor(desc:)")));
@end;

__attribute__((swift_name("Multiplatform_settingsSettings")))
@protocol CommonMultiplatform_settingsSettings
@required
- (id<CommonMultiplatform_settingsSettingsListener>)addListenerKey:(NSString *)key callback:(CommonKotlinUnit *(^)(void))callback __attribute__((swift_name("addListener(key:callback:)")));
- (void)clear __attribute__((swift_name("clear()")));
- (BOOL)getBooleanKey:(NSString *)key defaultValue:(BOOL)defaultValue __attribute__((swift_name("getBoolean(key:defaultValue:)")));
- (double)getDoubleKey:(NSString *)key defaultValue:(double)defaultValue __attribute__((swift_name("getDouble(key:defaultValue:)")));
- (float)getFloatKey:(NSString *)key defaultValue:(float)defaultValue __attribute__((swift_name("getFloat(key:defaultValue:)")));
- (int32_t)getIntKey:(NSString *)key defaultValue:(int32_t)defaultValue __attribute__((swift_name("getInt(key:defaultValue:)")));
- (int64_t)getLongKey:(NSString *)key defaultValue:(int64_t)defaultValue __attribute__((swift_name("getLong(key:defaultValue:)")));
- (NSString *)getStringKey:(NSString *)key defaultValue:(NSString *)defaultValue __attribute__((swift_name("getString(key:defaultValue:)")));
- (BOOL)hasKeyKey:(NSString *)key __attribute__((swift_name("hasKey(key:)")));
- (void)putBooleanKey:(NSString *)key value:(BOOL)value __attribute__((swift_name("putBoolean(key:value:)")));
- (void)putDoubleKey:(NSString *)key value:(double)value __attribute__((swift_name("putDouble(key:value:)")));
- (void)putFloatKey:(NSString *)key value:(float)value __attribute__((swift_name("putFloat(key:value:)")));
- (void)putIntKey:(NSString *)key value:(int32_t)value __attribute__((swift_name("putInt(key:value:)")));
- (void)putLongKey:(NSString *)key value:(int64_t)value __attribute__((swift_name("putLong(key:value:)")));
- (void)putStringKey:(NSString *)key value:(NSString *)value __attribute__((swift_name("putString(key:value:)")));
- (void)removeKey:(NSString *)key __attribute__((swift_name("remove(key:)")));
- (void)removeListenerListener:(id<CommonMultiplatform_settingsSettingsListener>)listener __attribute__((swift_name("removeListener(listener:)")));
@end;

__attribute__((swift_name("KotlinIterator")))
@protocol CommonKotlinIterator
@required
- (BOOL)hasNext __attribute__((swift_name("hasNext()")));
- (id _Nullable)next __attribute__((swift_name("next()")));
@end;

__attribute__((swift_name("KotlinException")))
@interface CommonKotlinException : CommonKotlinThrowable
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(CommonKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(CommonKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((swift_name("Kotlinx_serialization_runtime_nativeCompositeEncoder")))
@protocol CommonKotlinx_serialization_runtime_nativeCompositeEncoder
@required
- (void)encodeBooleanElementDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index value:(BOOL)value __attribute__((swift_name("encodeBooleanElement(desc:index:value:)")));
- (void)encodeByteElementDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index value:(int8_t)value __attribute__((swift_name("encodeByteElement(desc:index:value:)")));
- (void)encodeCharElementDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index value:(unichar)value __attribute__((swift_name("encodeCharElement(desc:index:value:)")));
- (void)encodeDoubleElementDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index value:(double)value __attribute__((swift_name("encodeDoubleElement(desc:index:value:)")));
- (void)encodeFloatElementDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index value:(float)value __attribute__((swift_name("encodeFloatElement(desc:index:value:)")));
- (void)encodeIntElementDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index value:(int32_t)value __attribute__((swift_name("encodeIntElement(desc:index:value:)")));
- (void)encodeLongElementDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index value:(int64_t)value __attribute__((swift_name("encodeLongElement(desc:index:value:)")));
- (void)encodeNonSerializableElementDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index value:(id)value __attribute__((swift_name("encodeNonSerializableElement(desc:index:value:)")));
- (void)encodeNullableSerializableElementDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index serializer:(id<CommonKotlinx_serialization_runtime_nativeSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeNullableSerializableElement(desc:index:serializer:value:)")));
- (void)encodeSerializableElementDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index serializer:(id<CommonKotlinx_serialization_runtime_nativeSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeSerializableElement(desc:index:serializer:value:)")));
- (void)encodeShortElementDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index value:(int16_t)value __attribute__((swift_name("encodeShortElement(desc:index:value:)")));
- (void)encodeStringElementDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index value:(NSString *)value __attribute__((swift_name("encodeStringElement(desc:index:value:)")));
- (void)encodeUnitElementDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index __attribute__((swift_name("encodeUnitElement(desc:index:)")));
- (void)endStructureDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc __attribute__((swift_name("endStructure(desc:)")));
- (BOOL)shouldEncodeElementDefaultDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index __attribute__((swift_name("shouldEncodeElementDefault(desc:index:)")));
@property (readonly) id<CommonKotlinx_serialization_runtime_nativeSerialContext> context;
@end;

__attribute__((swift_name("Kotlinx_serialization_runtime_nativeEnumDescriptor")))
@interface CommonKotlinx_serialization_runtime_nativeEnumDescriptor : CommonKotlinx_serialization_runtime_nativeSerialClassDescImpl
- (instancetype)initWithName:(NSString *)name choices:(CommonKotlinArray *)choices __attribute__((swift_name("init(name:choices:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithName:(NSString *)name generatedSerializer:(id<CommonKotlinx_serialization_runtime_nativeGeneratedSerializer> _Nullable)generatedSerializer __attribute__((swift_name("init(name:generatedSerializer:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@end;

__attribute__((swift_name("Kotlinx_serialization_runtime_nativeSerialContext")))
@protocol CommonKotlinx_serialization_runtime_nativeSerialContext
@required
- (id<CommonKotlinx_serialization_runtime_nativeKSerializer> _Nullable)getKclass:(id<CommonKotlinKClass>)kclass __attribute__((swift_name("get(kclass:)")));
- (id<CommonKotlinx_serialization_runtime_nativeKSerializer> _Nullable)getByValueValue:(id)value __attribute__((swift_name("getByValue(value:)")));
@end;

__attribute__((swift_name("KotlinAnnotation")))
@protocol CommonKotlinAnnotation
@required
@end;

__attribute__((swift_name("Kotlinx_serialization_runtime_nativeSerialKind")))
@interface CommonKotlinx_serialization_runtime_nativeSerialKind : KotlinBase
@end;

__attribute__((swift_name("Kotlinx_serialization_runtime_nativeCompositeDecoder")))
@protocol CommonKotlinx_serialization_runtime_nativeCompositeDecoder
@required
- (BOOL)decodeBooleanElementDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index __attribute__((swift_name("decodeBooleanElement(desc:index:)")));
- (int8_t)decodeByteElementDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index __attribute__((swift_name("decodeByteElement(desc:index:)")));
- (unichar)decodeCharElementDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index __attribute__((swift_name("decodeCharElement(desc:index:)")));
- (int32_t)decodeCollectionSizeDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc __attribute__((swift_name("decodeCollectionSize(desc:)")));
- (double)decodeDoubleElementDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index __attribute__((swift_name("decodeDoubleElement(desc:index:)")));
- (int32_t)decodeElementIndexDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc __attribute__((swift_name("decodeElementIndex(desc:)")));
- (float)decodeFloatElementDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index __attribute__((swift_name("decodeFloatElement(desc:index:)")));
- (int32_t)decodeIntElementDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index __attribute__((swift_name("decodeIntElement(desc:index:)")));
- (int64_t)decodeLongElementDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index __attribute__((swift_name("decodeLongElement(desc:index:)")));
- (id _Nullable)decodeNullableSerializableElementDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index deserializer:(id<CommonKotlinx_serialization_runtime_nativeDeserializationStrategy>)deserializer __attribute__((swift_name("decodeNullableSerializableElement(desc:index:deserializer:)")));
- (id _Nullable)decodeSerializableElementDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index deserializer:(id<CommonKotlinx_serialization_runtime_nativeDeserializationStrategy>)deserializer __attribute__((swift_name("decodeSerializableElement(desc:index:deserializer:)")));
- (int16_t)decodeShortElementDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index __attribute__((swift_name("decodeShortElement(desc:index:)")));
- (NSString *)decodeStringElementDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index __attribute__((swift_name("decodeStringElement(desc:index:)")));
- (void)decodeUnitElementDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index __attribute__((swift_name("decodeUnitElement(desc:index:)")));
- (void)endStructureDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc __attribute__((swift_name("endStructure(desc:)")));
- (id _Nullable)updateNullableSerializableElementDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index deserializer:(id<CommonKotlinx_serialization_runtime_nativeDeserializationStrategy>)deserializer old:(id _Nullable)old __attribute__((swift_name("updateNullableSerializableElement(desc:index:deserializer:old:)")));
- (id _Nullable)updateSerializableElementDesc:(id<CommonKotlinx_serialization_runtime_nativeSerialDescriptor>)desc index:(int32_t)index deserializer:(id<CommonKotlinx_serialization_runtime_nativeDeserializationStrategy>)deserializer old:(id _Nullable)old __attribute__((swift_name("updateSerializableElement(desc:index:deserializer:old:)")));
@property (readonly) id<CommonKotlinx_serialization_runtime_nativeSerialContext> context;
@property (readonly) CommonKotlinx_serialization_runtime_nativeUpdateMode *updateMode;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinNothing")))
@interface CommonKotlinNothing : KotlinBase
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_serialization_runtime_nativeUpdateMode")))
@interface CommonKotlinx_serialization_runtime_nativeUpdateMode : CommonKotlinEnum
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
@property (class, readonly) CommonKotlinx_serialization_runtime_nativeUpdateMode *banned;
@property (class, readonly) CommonKotlinx_serialization_runtime_nativeUpdateMode *overwrite;
@property (class, readonly) CommonKotlinx_serialization_runtime_nativeUpdateMode *update;
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (int32_t)compareToOther:(CommonKotlinx_serialization_runtime_nativeUpdateMode *)other __attribute__((swift_name("compareTo(other:)")));
@end;

__attribute__((swift_name("Multiplatform_settingsSettingsListener")))
@protocol CommonMultiplatform_settingsSettingsListener
@required
@end;

__attribute__((swift_name("KotlinKDeclarationContainer")))
@protocol CommonKotlinKDeclarationContainer
@required
@end;

__attribute__((swift_name("KotlinKAnnotatedElement")))
@protocol CommonKotlinKAnnotatedElement
@required
@end;

__attribute__((swift_name("KotlinKClassifier")))
@protocol CommonKotlinKClassifier
@required
@end;

__attribute__((swift_name("KotlinKClass")))
@protocol CommonKotlinKClass <CommonKotlinKDeclarationContainer, CommonKotlinKAnnotatedElement, CommonKotlinKClassifier>
@required
- (BOOL)isInstanceValue:(id _Nullable)value __attribute__((swift_name("isInstance(value:)")));
@property (readonly) NSString * _Nullable qualifiedName;
@property (readonly) NSString * _Nullable simpleName;
@end;

NS_ASSUME_NONNULL_END

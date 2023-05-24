3. 註冊 - 持久層 
通過MyBatis操作資料庫。

3.1 規劃需要執行的SQL語句
1. 使用者的註冊功能，相當於資料的插入操作
insert into t_user (user, password) values()
2. 在使用者註冊時，首先要查詢使用者是否已經存在，如果存在就不能註冊，
select * from t_user where username =?

3.2 設計接口和抽象方法
1. 定義Mapper接口。在項目的目錄結構下建立一個mapper包，
在這個包下再根據不同的功能模組來建立mapper接口。
建立一個UserMapper接口，定義這兩個SQL接口語句的抽象方法
2. 在啟動類配置mapper接口文件的位置
@MapperScan("com.sp.mapper")

3.3 編寫映射
1. 定義xml映射文件，與對應的接口進行關聯。
所有的影射文件需要放在resources目錄下，在這個目錄下建立一個mapper資料夾，然後在這個資料夾下存放Mapper的映射文件
2. 建立接口對應的映射文件，遵循和接口文件的名稱保持一致。
UserMapper.xml
3. 配置接口中的方法對應上SQL語句，需要借助標籤來完成，insert / update / delete / select，對應的就是增刪改查操作
4. 將mapper文件的位置註冊到properties對應的配置文件中 
5. 單元測試：每個獨立的層編寫完畢後，需要編寫單元測試方法，來測試目前的功能。在test包下建立一個mapper包，在這個包下再建立一個持久層的測試
縮小查詢程式碼錯誤的範圍
在團隊轉移時，可以確認部分模組是沒有問題的

4. 註冊 - 業務層
4.1 規劃異常
1. RuntimeException異常，做為這異常的子類，然後再去定義具體的異常類型來繼承這個異常。 
建立業務層異常的基類，ServiceException異常。這個異常繼承RuntimeException異常。異常機制的建立
根據業務層的不同的功能來詳細定義具體的異常的類型，統一去繼承ServiceException異常類
2. 使用者名稱被占用，拋出一個異常：UsernameDuplicatedException
3. 正在執行資料插入操作時，伺服器、資料庫當機。處於正在執行插入的過程中所產生的異常：InsertException異常
4.2 設計接口和抽象方法的定義
1. 在service包下建立一個UserService接口
2. 建立一個實現類UserServiceImpl，實現UserService接口的抽象方法
3. 單元測試包下建立一個UserServiceTests類，增加單元測試類的功能

5. 註冊 - 控制層
5.1 建立響應
狀態碼、狀態描述訊息、資料。將這部分功能封裝在一個類中，將這個類作為方法的返回值，返回給前端瀏覽器
5.2 設計請求
依據目前的業務功能模組進行請求的設計
請求路徑: /users/reg
請求參數: User user
請求類型: POST
響應結果: JsonResult<void>
5.3 處理請求
1. 建立一個控制層對應的類UserController類，依賴於業務層的接口
5.4 控制層進行優化設計
在控制層抽離一個父類，統一去處理異常相關的操作，BaseController類

6. 註冊 - 前端頁面
1. 在register頁面中編寫發送請求的方法，點擊事件來完成。$("選擇器")，$.ajax()發送非同步請求
2. JQuery封裝了一個函式，$.ajax，$被封裝成一個物件，通過$調用ajax()函式，可以非同步加載相關的請求
3. ajax使用方式，需要一個方法體做為方法的參數使用。參數順序沒有要求
   $.ajax({
        url: "", // 請求地址
        type:"", // 請求類型(GET、POST)
        data:"", // 向指定的請求url地址提交的資料(例如："username=u1&password=p1")
        dataType:"", // 提交的資料的類型。一般指定為json類型。
        success: functon(){}, // 當伺服器正常響應用戶端時，會自動調用success方法，並將伺服器返回的資料以參數的形式傳遞給這個方法的參數
        error: functon(){} // // 當伺服器非正常響應用戶端時，會自動調用error方法，並將伺服器返回的資料以參數的形式傳遞給這個方法的參數
   });
7. 登入
當使用者輸入帳號和密碼將資料提交給後台進行查詢，如果存在，則表示登入成功，成功之後跳轉到主頁
1. 持久層
1.1 規劃需要執行的SQL語句
根據使用者提交的帳號和密碼作select查詢。密碼的比較在業務層進行
select * from t_user where username=?
如果在分析過程中發現某個功能模組已經被開發完成了，可以省略
1.2 接口設計和抽象方法
不用重複開發。單元測試也是
不用重複開發
2. 業務層
2.1 規劃異常
1. 帳號對應的密碼錯誤，密碼匹配異常。PasswordNotMatchException異常，執行時異常，業務層異常
2. 帳號找不到。UsernameNotFoundException，執行時異常，業務層異常
3. 異常的編寫：
· 業務層異常需要繼承ServiceException異常類
· 在具體的異常類中定義構造方法
2.2 設計業務層接口和抽象方法
1. 直接在UserService接口中編寫抽象方法，login(String username, String password)，將目前登入成功的使用者資料以使用者物件(User)的形式進行返回，可以將資料保存在cookie或session中，可以避免重複度很高的資料多次頻繁操作資料進行取得(例如：帳號名、使用者id存在放session中，頭像存放在cookie中)
2. 在實現類中實現父接口的抽象方法
3. 測試類中測試業務登入的方法是否可以執行
2.3 抽象方法實現
3. 控制層
3.1 處理異常
業務層拋出的異常是什麼，需要在統一異常處理類中進行統一的捕獲和處理。如果業務層拋出的異常類型已經處理過，不需要重複增加
3.2 設計請求
請求路徑：/user/login
請求方式：POST
請求資料：String username, String password, HttpSession session
響應結果：JsonResult<User>
3.3 處理請求
在UserController類中編寫處理請求的方法
4. 前端頁面
1. 在login.html頁面中依據面所設置的請求來發送ajax請求
2. 放問頁面進行使用者的登入操作

7. 使用者登入會話Session
主要儲存在伺服器端，用於保存伺服器的臨時資料的物件，所保存的資料可以在整個專案中取得，
看作是一個共享的資料。首次登入時所取得的使用者資訊，轉移到session物件即可。
session.getAttrbute("key")可以將取得session中的資料進行封裝，封裝在BaseController中
1. 封裝session物件中資料的取得(封裝在父類中)、設置(登入成功後進行資料的設置，設置到全域的session物件中)
2. 在父類中封裝兩個資料：取得uid、取得username對應的兩個方法
3. 在登入的方法中將資料封裝在session物件中。伺服器自身自動建立session物件，已經是一個全域的sesseion物件了。
SpringBoot直接使用session物件，直接將HttpSession類型的物件作為請求處理方法的參數，會自動將全域的session物件注入到請求處理方法的session形參上

8. 攔截器
首先將所有的請求統一攔截到攔截器中，可以在攔截器中定義過濾的規則，如果不滿足系統設置的過濾規則，
統一的處理是重新去打開login.html頁面(重定向 和 轉發)，推薦使用重定向。
在SpringBoot中攔截器的定義和使用。是依靠SpringMVC來完成的，SpringMVC提供了一個HandlerInterceptor接口，用於表示定義一個攔截器。首先自定義一個類，再讓這個類實現接口。
1. 自定義一個類，實現HandlerInterceptor接口
2. 註冊過濾器：增加白名單(不登入可訪問)、黑名單(登入才可訪問)
3. 註冊過濾器的技術：借助WebMvcConfigure接口，可以將使用者定義的攔截器進行註冊，註冊後才可以保證攔截器生效及使用。定義一個類，實現WebMvcConfigure接口。建議存放在專案的Config包結構下。

9. 加入購物車 - 持久層
1. 建立資料庫
2. 建立實體類
2.1 規劃需要執行的SQL語句
1. 向購物車表中插入資料
insert into t_cart(aid除外) value ()
2. 當購物車中已經存在商品，直接更新num數量
update t_cart set num=? where cid=
3. 在插入或更新具體執行哪個語句，取決於資料庫中是否有目前這個購物車商的資料
select * from t_cart where cid=? and uid=?
2.2 設計接口和抽象方法
建立一個CartMapper接口持久層文件
2.3 SQL映射
建立一個CarMapper.xml文件，將字段設置為一致，增加以上三個抽象方法的SQL語句映射

10. 加入購物車 - 業務層
1.1 規劃異常
1. 插入資料時可能產生異常：InsertException
2. 更新資料時可能產生異常：UpdateException
1.2 規劃接口和抽象方法
建立CartService接口
1.3 設計實現類
建立CartServiceImpl
1.4 建立測試類

11. 加入購物車 - 控制層
1. 沒有需要處裡的異常
2. 請求處理設計
3. 請求處理方法的編寫。建立CartController類
4. 先登入再訪問url地址進行資料的測試

12. 加入購物車 - 前端
在product.html頁面給「加入購物車」增加點擊事件，並發送ajax事件

在ajax函式中data參數的資料設置的方式
data:$("form表單選擇器").serialize()：適合表單中手動輸入字串或是可以選擇的類型。當參數過多並且在同一個表單中，字串的提交等
data:new FormData($("form表單選擇器")[0])。只適用提交文件
data:"username=Tom"。適合參數量少，並且非動態值。可以進行手動拼接
// 適用JSON格式提交資料
data: {
    "username": "tom",
    "age": 18, 
    "sex": 0
}

13. 展示購物車列表
1. 持久層
1.1 規劃SQL語句
SELECT cid, uid, pid, t_cart.price, t_cart.num, t_product.image, t_product.title, t_product.price AS realprice
FROM t_cart LEFT JOIN t_product ON t_cart.pid = t_product.id
WHERE uid=#{uid}
ORDER BY t_cart.created_time DESC
1.2 技士接口和抽象方法
1.3 單元測試
2. 業務層
1. 編寫業務層的接口方法
List<CartVO> getVOByUid(Integer uid);
2. 在實現類中實現此方法
3. 控制層
1. 設計請求
2. 實現請求處理方法的程式碼編寫
3. 先登入在進行功能測試
4. 前端頁面

14. 增加購物車商品數量
1. 持久層
1.1 規劃SQL語句
1. 更新t_cart表紀錄的num的值
update t_cart set num=?, modified_user=?, modified_time=? where cid
2. 根據cid的值來查詢目前的購物車中這條紀錄是否存在
SELECT * FROM t_cart WHERE cid=#{cid}
1.2 接口和抽象方法
1.3 SQL映射文件
1.4 單元測試
2. 業務層
2.1 規劃異常
1. 更新時會產生更新異常
2. 查詢到的資料是否有訪問到的權限
3. 要查詢的資料不存在，CartNotFoundException
2.2 接口和抽象方法
2.3 實現類
3. 控制層
3.1 處理異常
3.2 設計請求
3.3 處理請求
4. 前端頁面

15. 顯示勾選的商品
1. 持久層
1.1 規劃SQL語句
勾選購物車列表商品。點擊結算後，跳轉到結算頁面。這個頁面需要展示對應的商品資料
展示的內容還是購物車的表。需要將勾選的商品cid傳遞給下一個頁面
1.2 接口和抽象方法
1.3 SQL映射
2.業務層
1. 沒有需要規劃的異常
2. 設計業務層接口中的抽象方法
3. 實現抽象方法
3. 控制層
1.請求設計
2.完成請求控制方法的定義和聲明
4. 前端頁面
1. 將cart.html頁面中結算按鈕屬性button更改為submit
2. orderConfirm.html中添加自動載入傳遞過來的cids資料，再去請求ajax，填充頁面顯示的區域

16. 建立訂單
1. 資料庫
2. 實體類
1. 訂單實體類
2. 商品詳情實體類
3. 持久層
3.1規劃SQL語句
1將資料插入訂單表中
insert into t_order (id除外所有字段) VALUE (字段的值)
2將資料插入到訂單詳情的表中
   insert into t_order (id除外所有字段) VALUE (字段的值)
3.2 接口和抽象方法
OrderMapper接口，接口中增加兩個SQL語句的方法
3.3 SQL映射
OrderMapper.xml
4. 業務層
在service包下建立OrderService接口，增加抽象方法用於建立訂單
5. 前端設計
1. 設計請求
2. 建立OrderController類，編寫請求處理方法
6. 前端頁面


新增收貨地址
1. 持久層
1.1 各功能開發順序
新增收貨地址、列表展示、默認收貨地址、刪除地址、修改地址
1.2 規劃SQL語句

訂單詳情頁面
部署、github
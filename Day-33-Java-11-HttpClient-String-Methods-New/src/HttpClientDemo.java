import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class HttpClientDemo {

    public static void main(String[] args) {
        
        // 1. Tạo Client (nên tạo 1 lần và tái sử dụng)
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        // 2. Tạo Request (Yêu cầu)
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/todos/1"))
                .header("Accept", "application/json")
                .GET() // Mặc định là GET, nhưng ghi rõ cho dễ đọc
                .build();

        System.out.println("Main: Bắt đầu gửi yêu cầu Async...");

        // 3. Gửi Yêu cầu Bất đồng bộ (Async)
        // Nó trả về CompletableFuture ngay lập tức!
        CompletableFuture<HttpResponse<String>> futureResponse = client.sendAsync(
                request,
                HttpResponse.BodyHandlers.ofString() // Chỉ định ta muốn body là String
        );

        // 4. Main thread tự do làm việc khác
        System.out.println("Main: Đã gửi. Đang chờ kết quả... (Main thread rảnh!)");

        // 5. Xử lý kết quả khi nó quay về (dùng chaining của CompletableFuture)
        CompletableFuture<String> finalResult = futureResponse.thenApply(response -> {
            System.out.println("Callback: Đã nhận phản hồi (Thread: " + Thread.currentThread().getName() + ")");
            System.out.println("Status Code: " + response.statusCode());
            return response.body(); // Chỉ trả về body
        });

        // 6. Chờ (join) kết quả cuối cùng (chỉ để demo)
        try {
            String body = finalResult.join();
            System.out.println("Main: Kết quả cuối cùng:\n" + body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
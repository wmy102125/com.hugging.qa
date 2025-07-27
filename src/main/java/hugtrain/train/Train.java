package hugtrain.train;

import hugtrain.api.HuggingFaceAPI;
import hugtrain.inference.LocalModelInference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
@Component
public class Train {
    @Autowired
    private HuggingFaceAPI huggingFaceAPI;
    @Autowired
    private LocalModelInference localModelInference;

    public void train() {
        try {
            // 读取测试数据
            String input = Files.readString(Paths.get("data/test_input.txt"));

            // 方式1：调用远程API
            System.out.println("=== API 调用结果 ===");
            System.out.println(huggingFaceAPI.query(input));

            // 方式2：本地模型推理
            System.out.println("\n=== 本地推理结果 ===");
            System.out.println(localModelInference.predict(input));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

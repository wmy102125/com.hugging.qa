package hugtrain.inference;

import ai.djl.huggingface.tokenizers.HuggingFaceTokenizer;
import ai.djl.modality.Classifications;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;
import ai.djl.translate.TranslateException;
import org.springframework.stereotype.Component;

@Component
public class LocalModelInference {
    public  Classifications predict(String text) throws Exception {
        // 1. 加载分词器
        HuggingFaceTokenizer tokenizer = HuggingFaceTokenizer.newInstance("bert-base-chinese");

        // 2. 定义模型标准
        Criteria<String, Classifications> criteria = Criteria.builder()
                .setTypes(String.class, Classifications.class)
                .optModelUrls("djl://ai.djl.huggingface.pytorch/bert-base-chinese")
                .optProgress(new ProgressBar())
                .build();
        // 2. 加载模型
        try (ZooModel<String, Classifications> model = criteria.loadModel()) {
            // 3. 创建预测器并执行预测
            try (var predictor = model.newPredictor()) {
                return predictor.predict(text); // 此处不再报错
            }
        } catch (TranslateException e) {
            throw new RuntimeException("预测失败: " + e.getMessage(), e);
        }
    }

//    public static void main(String[] args) throws Exception {
//        String input = "I love Java and Hugging Face!";
//        Classifications result = predict(input);
//        System.out.println(result); // 输出分类结果
//    }
}
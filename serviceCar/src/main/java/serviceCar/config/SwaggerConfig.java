package serviceCar.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket testApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("test").genericModelSubstitutes(DeferredResult.class)
				// .genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false).forCodeGeneration(true).pathMapping("/")// base，最终调用接口后会和paths拼接在一起
				.select().paths(or(regex("/v1_0/.*")))// 过滤的接口
				.build().apiInfo(testApiInfo());
	}

	private ApiInfo testApiInfo() {
		ApiInfo apiInfo = new ApiInfo("供车管理API", // 大标题
				"供车管理API", // 小标题
				"1.0", // 版本
				"", new Contact("Luke Yale", "", ""), "", // 链接显示文字
				""// 网站链接
		);

		return apiInfo;
	}
}

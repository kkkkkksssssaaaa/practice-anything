package common.extension

import common.factory.models.annotations.Component
import common.factory.models.annotations.Controller
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ClassExtendsFunctionTest {
    @Nested
    @DisplayName("KClass.isBean")
    internal inner class IsBeanTest {
        @Test
        fun `Component 어노테이션이 선언된 클래스는 isBean=true 이다`() {
            @Component class HasComponent

            assertTrue {
                HasComponent::class.isBean()
            }
        }

        @Test
        fun `Controller 어노테이션이 선언된 클래스는 isBean=true 이다`() {
            @Controller class HasComponent

            assertTrue {
                HasComponent::class.isBean()
            }
        }

        @Test
        fun `stereotype 에 해당하지 않는 어노테이션이 선언된 클래스는 isBean=false 이다`() {
            @NotStereotypeAnnotation class HasComponent

            assertFalse {
                HasComponent::class.isBean()
            }
        }

        @Test
        fun `아무런 어노테이션이 선언 되지 않은 클래스는 isBean=false 이다`() {
            class HasComponent

            assertFalse {
                HasComponent::class.isBean()
            }
        }
    }
}

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
private annotation class NotStereotypeAnnotation()
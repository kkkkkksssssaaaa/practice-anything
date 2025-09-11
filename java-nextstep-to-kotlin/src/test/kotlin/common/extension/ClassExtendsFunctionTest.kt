package common.extension

import common.factory.models.annotations.Component
import common.factory.models.servlet.annotations.Controller
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ClassExtendsFunctionTest {
    @Nested
    @DisplayName("KClass.isBean")
    internal inner class IsBeanTest {
        @Test
        fun `Component 어노테이션이 선언된 클래스는 isBean=true 이다`() {
            @Component class HasAnnotation

            assertTrue {
                HasAnnotation::class.isBean()
            }
        }

        @Test
        fun `Controller 어노테이션이 선언된 클래스는 isBean=true 이다`() {
            @Controller
            class HasAnnotation

            assertTrue {
                HasAnnotation::class.isBean()
            }
        }

        @Test
        fun `stereotype 에 해당하지 않는 어노테이션이 선언된 클래스는 isBean=false 이다`() {
            @NotStereotypeAnnotation class HasNotAnnotation

            assertFalse {
                HasNotAnnotation::class.isBean()
            }
        }

        @Test
        fun `아무런 어노테이션이 선언 되지 않은 클래스는 isBean=false 이다`() {
            class HasNotAnnotation

            assertFalse {
                HasNotAnnotation::class.isBean()
            }
        }
    }

    @Nested
    @DisplayName("KClass.isBean(param)")
    internal inner class IsBeanWithParamTest {
        @Nested
        @DisplayName("stereotype 에 해당하는 어노테이션이 인자로 주어질 때")
        internal inner class IsBeanWithStereotypeParamTest {
            @Test
            fun `대상 클래스에 인자와 동일한 어노테이션이 선언된 경우 isBean=true 이다`() {
                @Component class HasAnnotation

                assertTrue {
                    HasAnnotation::class.isBean<Annotation>(Component::class)
                }
            }

            @Test
            fun `대상 클래스에 인자와 다른 어노테이션이 선언된 경우 isBean=false 이다`() {
                @Component class HasAnnotation

                assertFalse {
                    HasAnnotation::class.isBean<Annotation>(Controller::class)
                }
            }

            @Test
            fun `대상 클래스에 stereotype 어노테이션이 선언 되지 않은 경우 isBean=false 이다`() {
                class HasNotAnnotation

                assertFalse {
                    HasNotAnnotation::class.isBean<Annotation>(Component::class)
                }

                assertFalse {
                    HasNotAnnotation::class.isBean<Annotation>(Controller::class)
                }
            }
        }

        @Nested
        @DisplayName("stereotype 에 해당하지 않는 어노테이션이 인자로 주어질 때")
        internal inner class IsBeanWithNotStereotypeParamTest {
            @Test
            fun `IllegalArgumentException 을 던진다`() {
                @Component class HasAnnotation

                assertThrows<IllegalArgumentException> {
                    HasAnnotation::class.isBean<Annotation>(NotStereotypeAnnotation::class)
                }
            }
        }
    }
}

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
private annotation class NotStereotypeAnnotation()
package admin.gpuserver.domain;

import admin.gpuserver.exception.GpuServerServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GpuServerTest {
    private Lab lab;

    @BeforeEach
    void setUp() {
        lab = new Lab("랩1");
    }

    @DisplayName("생성 테스트 - 정상")
    @Test
    void 생성() {
        assertThatCode(() -> new GpuServer("GPU서버1", true, 1024L, 1024L, lab))
                .doesNotThrowAnyException();
    }

    @DisplayName("생성 테스트 - 이름이 null")
    @Test
    void 생성_이름_null() {
        assertThatThrownBy(() -> new GpuServer(null, true, 1024L, 1024L, lab))
                .isInstanceOf(GpuServerServiceException.class)
                .hasMessage("객체를 생성할 수 없습니다.");
    }

    @DisplayName("생성 테스트 - 이름이 빈문자열")
    @Test
    void 생성_이름_빈문자열() {
        assertThatThrownBy(() -> new GpuServer("", true, 1024L, 1024L, lab))
                .isInstanceOf(GpuServerServiceException.class)
                .hasMessage("객체를 생성할 수 없습니다.");
    }

    @DisplayName("생성 테스트 - IsOn이 null")
    @Test
    void 생성_IsOn_null() {
        assertThatThrownBy(() -> new GpuServer("GPU서버1", null, 1024L, 1024L, lab))
                .isInstanceOf(GpuServerServiceException.class)
                .hasMessage("객체를 생성할 수 없습니다.");
    }

    @DisplayName("생성 테스트 - MemorySize이 null")
    @Test
    void 생성_MemorySize_null() {
        assertThatThrownBy(() -> new GpuServer("GPU서버1", true, null, 1024L, lab))
                .isInstanceOf(GpuServerServiceException.class)
                .hasMessage("객체를 생성할 수 없습니다.");
    }

    @DisplayName("생성 테스트 - MemorySize이 0이하")
    @ParameterizedTest
    @ValueSource(longs = {-1024, -1, 0})
    void 생성_MemorySize_0이하(Long input) {
        assertThatThrownBy(() -> new GpuServer("GPU서버1", true, input, 1024L, lab))
                .isInstanceOf(GpuServerServiceException.class)
                .hasMessage("객체를 생성할 수 없습니다.");
    }

    @DisplayName("생성 테스트 - DiskSize이 null")
    @Test
    void 생성_DiskSize_null() {
        assertThatThrownBy(() -> new GpuServer("GPU서버1", true, 1024L, null, lab))
                .isInstanceOf(GpuServerServiceException.class)
                .hasMessage("객체를 생성할 수 없습니다.");
    }

    @DisplayName("생성 테스트 - DiskSize이 0이하")
    @ParameterizedTest
    @ValueSource(longs = {-1024, -1, 0})
    void 생성_DiskSize_0이하(Long input) {
        assertThatThrownBy(() -> new GpuServer("GPU서버1", true, 1024L, input, lab))
                .isInstanceOf(GpuServerServiceException.class)
                .hasMessage("객체를 생성할 수 없습니다.");
    }

    @DisplayName("생성 테스트 - Lab이 null")
    @Test
    void 생성_Lab_null() {
        assertThatThrownBy(() -> new GpuServer("GPU서버1", true, 1024L, 1024L, null))
                .isInstanceOf(GpuServerServiceException.class)
                .hasMessage("객체를 생성할 수 없습니다.");
    }
}
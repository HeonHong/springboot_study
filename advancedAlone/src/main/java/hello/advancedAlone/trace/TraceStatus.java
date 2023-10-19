package hello.advancedAlone.trace;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
public class TraceStatus {

    private TraceId traceId;
    private Long startMillis;
    private String message;

}

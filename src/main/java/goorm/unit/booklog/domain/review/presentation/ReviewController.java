package goorm.unit.booklog.domain.review.presentation;

import goorm.unit.booklog.common.exception.ExceptionResponse;
import goorm.unit.booklog.domain.review.application.ReviewService;
import goorm.unit.booklog.domain.review.presentation.request.ReviewCreateRequest;
import goorm.unit.booklog.domain.review.presentation.response.ReviewPersistResponse;
import goorm.unit.booklog.domain.review.presentation.response.ReviewResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
@Tag(name = "Review", description = "독후감 관리 api / 담당자 : 장선우")
public class ReviewController {
    private final ReviewService reviewService;

    @Operation(summary = "독후감 생성", description = "독후감을 생성합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "독후감 생성 성공",
                    content = @Content(schema = @Schema(implementation = ReviewPersistResponse.class))
            )
    })
    @ResponseStatus(CREATED)
    @PostMapping
    public ResponseEntity<ReviewPersistResponse> createReview(
            @Valid @RequestBody ReviewCreateRequest request) {
        ReviewPersistResponse response = reviewService.createReview(request);
        return ResponseEntity.status(CREATED).body(response);
    }

    @Operation(summary = "독후감 조회", description = "review_id에 일치하는 독후감을 조회합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "독후감 조회 성공",
                    content = @Content(schema = @Schema(implementation = ReviewResponse.class))
            ),
            @ApiResponse(
                    responseCode="404",
                    description="해당 reviewId가 존재하지 않습니다.",
                    content=@Content(schema=@Schema(implementation = ExceptionResponse.class))
            )
    })
    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> getReview(
            @Parameter(description = "독후감 ID", example = "1", required = true) @PathVariable("reviewId") @Positive Long reviewId) {
        ReviewResponse response = reviewService.getReview(reviewId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "독후감 수정", description = "review_id에 일치하는 독후감을 수정합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "독후감 수정 성공",
                    content = @Content(schema = @Schema(implementation = ReviewResponse.class))
            ),
            @ApiResponse(
                    responseCode="404",
                    description="해당 reviewId가 존재하지 않습니다.",
                    content=@Content(schema=@Schema(implementation = ExceptionResponse.class))
            )
    })
    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> updateReview(
            @Parameter(description = "독후감 ID", example = "1", required = true) @PathVariable("reviewId") @Positive Long reviewId,
            @Valid @RequestBody ReviewCreateRequest request) {
        ReviewResponse response = reviewService.updateReview(reviewId,request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "독후감 삭제", description = "review_id에 일치하는 독후감을 삭제합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "독후감 삭제 성공"
            ),
            @ApiResponse(
                    responseCode="404",
                    description="해당 reviewId가 존재하지 않습니다.",
                    content=@Content(schema=@Schema(implementation = ExceptionResponse.class))
            )
    })
    @PatchMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@Parameter(description = "독후감 ID", example = "1", required = true) @PathVariable("reviewId") @Positive Long reviewId){
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok().build();
    }
}

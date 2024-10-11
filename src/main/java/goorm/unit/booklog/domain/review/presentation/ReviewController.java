package goorm.unit.booklog.domain.review.presentation;

import goorm.unit.booklog.common.exception.ExceptionResponse;
import goorm.unit.booklog.domain.review.application.ReviewService;
import goorm.unit.booklog.domain.review.presentation.request.ReviewCreateRequest;
import goorm.unit.booklog.domain.review.presentation.request.ReviewUpdateRequest;
import goorm.unit.booklog.domain.review.presentation.response.ReviewListResponse;
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

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        ),
        @ApiResponse(
            responseCode = "403",
            description = "사용자 인증에 실패하였습니다.",
            content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "해당 책이 존재하지 않습니다.",
            content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
        )
    })
    @ResponseStatus(CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ReviewPersistResponse> createReview(
        @Parameter(description = "첨부 사진", content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
        @RequestPart(value = "file", required = false) MultipartFile file,
        @Parameter(description = "독후감 내용", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
        @Valid @RequestPart ReviewCreateRequest request
    ) {
        ReviewPersistResponse response = reviewService.createReview(file, request);
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
            description="해당 독후감이 존재하지 않습니다.",
            content=@Content(schema=@Schema(implementation = ExceptionResponse.class))
        )
    })
    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> getReview(
            @Parameter(description = "독후감 ID", example = "1", required = true) @PathVariable("reviewId") @Positive Long reviewId
    ) {
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
    @PatchMapping(value = "/{reviewId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateReview(
        @Parameter(description = "독후감 ID", example = "1", required = true) @PathVariable("reviewId") @Positive Long reviewId,
        @Parameter(description = "첨부 사진", content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
        @RequestPart(value = "file", required = false) MultipartFile file,
        @Parameter(description = "독후감 내용", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
        @Valid @RequestPart ReviewUpdateRequest request
    ) {
        reviewService.updateReview(reviewId, file, request);
        return ResponseEntity.ok().build();
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
    @PatchMapping("/delete/{reviewId}")
    public ResponseEntity<Void> deleteReview(@Parameter(description = "독후감 ID", example = "1", required = true) @PathVariable("reviewId") @Positive Long reviewId){
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "특정 도서에 대한 독후감 조회", description = "bookId에 일치하는 독후감을 조회합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "독후감 조회 성공",
                    content = @Content(schema = @Schema(implementation = ReviewResponse.class))
            ),
            @ApiResponse(
                    responseCode="404",
                    description="해당 도서에 대한 독후감이 존재하지 않습니다.",
                    content=@Content(schema=@Schema(implementation = ExceptionResponse.class))
            )
    })
    @GetMapping("/lists/{bookId}")
    public ResponseEntity<ReviewListResponse> getReviewListByBook(
            @Parameter(description = "책 ID", example = "1", required = true) @PathVariable("bookId") @Positive Long bookId
    ) {
        ReviewListResponse response = reviewService.getReviewListByBook(bookId);
        return ResponseEntity.ok(response);
    }
}

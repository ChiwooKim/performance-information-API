package study.performance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.performance.domain.Performance;
import study.performance.dto.request.PerformanceInformationUpdateDto;
import study.performance.dto.request.PerformanceInformationRequestDto;
import study.performance.dto.response.PerformanceInformationResponseDto;
import study.performance.repository.PerformanceRepository;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final PerformanceRepository performanceRepository;

    @Transactional
    public void save(PerformanceInformationRequestDto requestDto) {
        performanceRepository.save(requestDto.toEntity());
    }

    public PerformanceInformationResponseDto get(Long performanceId) {
        Performance performance = performanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalArgumentException("해당 공연이 존재하지 않습니다. id=" + performanceId));

        return PerformanceInformationResponseDto.builder()
                .performanceName(performance.getPerformanceName())
                .performanceNumber(performance.getPerformanceNumber())
                .performanceLocation(performance.getPerformanceLocation())
                .startDate(performance.getStartDate())
                .endDate(performance.getEndDate())
                .runtime(performance.getRuntime())
                .age(performance.getAge())
                .price(performance.getPrice())
                .thumbnail(performance.getThumbnail())
                .build();
    }

    @Transactional
    public void update(Long performanceId, PerformanceInformationUpdateDto requestDto) {
        Performance performance = performanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalArgumentException("해당 공연이 존재하지 않습니다. id=" + performanceId));

        performance.update(requestDto.getPerformanceName(), requestDto.getPerformanceNumber(),
                requestDto.getPerformanceLocation(), requestDto.getStartDate(), requestDto.getEndDate(),
                requestDto.getRuntime(), requestDto.getAge(), requestDto.getPrice(), requestDto.getThumbnail());
    }

    @Transactional
    public void delete(Long performanceId) {
        Performance performance = performanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalArgumentException("해당 공연이 존재하지 않습니다. id=" + performanceId));

        performanceRepository.delete(performance);
    }
}

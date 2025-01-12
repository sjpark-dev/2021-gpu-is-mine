package admin.gpuserver.domain.repository;

import admin.gpuserver.domain.Job;
import admin.gpuserver.domain.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {
    Optional<Job> findByGpuBoardIdAndStatus(Long gpuId, JobStatus jobStatus);

    List<Job> findByGpuBoardIdAndStatusOrderByIdAsc(Long gpuId, JobStatus jobStatus);

    Integer countByGpuBoardIdAndStatus(Long gpuId, JobStatus jobStatus);
}

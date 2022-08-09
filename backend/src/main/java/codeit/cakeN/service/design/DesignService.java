package codeit.cakeN.service.design;

import codeit.cakeN.domain.design.Design;
import codeit.cakeN.domain.design.DesignRepository;
import codeit.cakeN.exception.design.DesignException;
import codeit.cakeN.exception.design.DesignExceptionType;
import codeit.cakeN.web.design.dto.DesignRequestDto;
import codeit.cakeN.web.design.dto.DesignUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DesignService {

    private final DesignRepository designRepository;

    /**
     * 케이크 디자인 등록(제작)
     * @param requestDto
     */
    @Transactional
    public void save(DesignRequestDto requestDto) throws DesignException {
        // DB에 저장
        designRepository.save(requestDto.toEntity());
    }

    /**
     * 케이크 디자인 리스트
     * TODO 현재 접속한 사용자가 만든 것만 보이도록
     * @return
     */
    public List<Design> showAllDesign() {
        return designRepository.findAll(Sort.by(Sort.Direction.DESC, "designId"));
    }

    /**
     * 제작한 케이크 정보 조회
     * @param id
     * @return
     * @throws DesignException
     */
    public DesignRequestDto showInfo(Long id) throws DesignException {
        Design design = designRepository.findById(id).orElseThrow(
                () -> new DesignException(DesignExceptionType.NOT_FOUND_DESIGN)
        );

        return new DesignRequestDto(design);
    }

    /**
     * 케이크 제작 정보 수정
     * @param requestDto
     */
    public void update(DesignUpdateDto requestDto) throws DesignException {
        Design design = designRepository.findById(requestDto.getId()).orElseThrow(
                () -> new DesignException(DesignExceptionType.NOT_FOUND_DESIGN)
        );
        design.update(requestDto);
    }

    /**
     * 제작한 케이크 삭제
     * @param id
     */
    public void delete(Long id) throws DesignException {
        Design design = designRepository.findById(id).orElseThrow(
                () -> new DesignException(DesignExceptionType.NOT_FOUND_DESIGN)
        );
        designRepository.deleteById(id);
    }
}

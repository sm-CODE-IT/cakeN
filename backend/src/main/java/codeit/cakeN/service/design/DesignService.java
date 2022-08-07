package codeit.cakeN.service.design;

import codeit.cakeN.domain.design.Design;
import codeit.cakeN.domain.design.DesignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DesignService {

    private final DesignRepository designRepository;

    public void save(Design design) {
        designRepository.save(design);
    }

    public List<Design> showAllDesign() {
        return designRepository.findAll(Sort.by(Sort.Direction.DESC, "designId"));
    }

    public Design showInfo(Long id) {
        return designRepository.findById(id).orElse(null);
    }

    public void update(Design design) {
        designRepository.save(design);
    }

    public void delete(Long id) {
        designRepository.deleteById(id);
    }
}

package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.Image;
import com.fishingbooker.ftn.dto.ImageDto;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ImageService {

    Set<Image> saveImages(List<ImageDto> images) throws IOException;
}

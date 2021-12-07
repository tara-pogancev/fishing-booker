package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.Image;
import com.fishingbooker.ftn.dto.ImageDto;
import com.fishingbooker.ftn.service.interfaces.ImageService;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {
    @Override
    public Set<Image> saveImages(List<ImageDto> images){
        Set<Image> savedImages=new HashSet<>();
        if (images==null){
            return null;
        }
        for (ImageDto image:images){
            String imageUrl=saveImage(image);
            savedImages.add(new Image(imageUrl));
        }

        return savedImages;
    }

    private String saveImage(ImageDto imageDto){
        if (imageDto.getContent().equalsIgnoreCase("exists")){ //ukoliko se prilikom editvanje vikendice prosljedi slka koja je vec sacuvana na serveru nemoj je opet cuvati
            return imageDto.getName(); //ime sadrzi url slike koji se skladisti u bazu
        }
        String[] parts = imageDto.getContent().split(",");
        String imageString = parts[1];
        byte[] decodedBytes = Base64.getDecoder().decode(imageString);
        BufferedImage buffImg = null;
        try {
            buffImg = ImageIO.read(new ByteArrayInputStream(decodedBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String basePath=System.getenv("PictureLocations");
        String name=imageDto.getName().replaceAll(" ", "_");
        String photoPath=basePath+ File.separator+name;
        File file = new File(photoPath);
        try {
            ImageIO.write(buffImg, imageDto.getExtension(), file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Image " + ".png" + " uploaded.");

        return "assets/images/"+name;
    }
}

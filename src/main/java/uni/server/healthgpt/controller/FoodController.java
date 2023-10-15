package uni.server.healthgpt.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uni.server.healthgpt.advice.exception.CTodayNotFoundException;
import uni.server.healthgpt.advice.exception.CTodayRemainedException;
import uni.server.healthgpt.advice.exception.UserNotFoundExceptionCustom;
import uni.server.healthgpt.data.entity.Food;
import uni.server.healthgpt.data.entity.User;
import uni.server.healthgpt.data.response.CommonResult;
import uni.server.healthgpt.data.response.SingleResult;
import uni.server.healthgpt.repo.UserJpaRepo;
import uni.server.healthgpt.service.impl.FoodService;
import uni.server.healthgpt.service.impl.ResponseService;
import uni.server.healthgpt.service.impl.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@PreAuthorize("hasRole('ROLE_USER')") //추가내용
@Api(tags = {"3. Food"}) // UserController를 대표하는 최상단 타이틀 영역에 표시될 값 세팅
@RequiredArgsConstructor // class 내부의 final 객체는 Constructor Injection 수행, @Autowired도 가능
@RestController // 결과를 JSON으로 도출
@RequestMapping(value = "/v1/food") // api resource를 버전별로 관리, /v1 을 모든 리소스 주소에 적용
public class FoodController {
    private final UserJpaRepo userJpaRepo; // Jpa를 활용한 CRUD 쿼리 가능
    private final ResponseService responseService; // 결과를 처리하는 Service
    private final UserService userService;
    private final FoodService foodService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token",
                    required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "유저에 아침 음식 추가", notes = "유저에 아침을 추가합니다")
    @PostMapping(value = "/setBreakfast")
    public User addBreakfast(@RequestParam String day,@RequestParam String foodName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        Optional<User> user = userJpaRepo.findByUid(id);

        return foodService.setBreakfast(user.orElseThrow(UserNotFoundExceptionCustom::new),day,foodName).orElseThrow(CTodayNotFoundException::new);
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token",
                    required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "유저에 점심 음식 추가", notes = "유저에 점심을 추가합니다")
    @PostMapping(value = "/setLunch")
    public User setLunch(@RequestParam String day,@RequestParam String foodName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        Optional<User> user = userJpaRepo.findByUid(id);

        return foodService.setLunch(user.orElseThrow(UserNotFoundExceptionCustom::new),day,foodName).orElseThrow(CTodayNotFoundException::new);
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token",
                    required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "유저에 저녁 음식 추가", notes = "유저에 저녁을 추가합니다")
    @PostMapping(value = "/setDinner")
    public User setDinner(@RequestParam String day,@RequestParam String foodName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        Optional<User> user = userJpaRepo.findByUid(id);

        return foodService.setDinner(user.orElseThrow(UserNotFoundExceptionCustom::new),day,foodName).orElseThrow(CTodayNotFoundException::new);
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token",
                    required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "유저에 점심칸 제거", notes = "유저에 점심 파트를 삭제합니다.")
    @DeleteMapping(value = "/removeLunch/{day}")
    public CommonResult deleteLunch(@PathVariable String day) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        Optional<User> user = userJpaRepo.findByUid(id);
        foodService.removeLunch(user.orElseThrow(UserNotFoundExceptionCustom::new),day).orElseThrow(CTodayNotFoundException::new);
        return responseService.getSuccessResult();
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token",
                    required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "유저에 아침칸 제거", notes = "유저에 아침 파트를 삭제합니다.")
    @DeleteMapping(value = "/removeBreakfast/{day}")
    public CommonResult deleteBreakfast(@PathVariable String day) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        Optional<User> user = userJpaRepo.findByUid(id);
        foodService.removeBreakfast(user.orElseThrow(UserNotFoundExceptionCustom::new),day).orElseThrow(CTodayNotFoundException::new);
        return responseService.getSuccessResult();
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token",
                    required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "유저에 저녁칸 제거", notes = "유저에 저녁 파트를 삭제합니다.")
    @DeleteMapping(value = "/removeDinner/{day}")
    public CommonResult deleteDinner(@PathVariable String day) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        Optional<User> user = userJpaRepo.findByUid(id);
        foodService.removeDinner(user.orElseThrow(UserNotFoundExceptionCustom::new),day).orElseThrow(CTodayNotFoundException::new);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "유저에 저녁칸 제거", notes = "유저에 저녁 파트를 삭제합니다.")
    @GetMapping(value = "/removeDinner/{day}")
    public SingleResult<List<Food>> getFoodList() {

        return responseService.getSingleResult(foodService.getFoodLists());
    }
}

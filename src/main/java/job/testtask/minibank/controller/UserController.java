package job.testtask.minibank.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import job.testtask.minibank.dto.UserSearchDto;
import job.testtask.minibank.entity.UserEntity;
import job.testtask.minibank.filter.FILTER_TYPE;
import job.testtask.minibank.service.MyUserDetailService;
import job.testtask.minibank.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//todo convert entity to dto ? cache?
@RequestMapping ("/user")
@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(description = "получение списка пользователей по фильтру" )
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Запрос успешно выполнен"),
            @ApiResponse( responseCode = "400", description = "Ошибка при запросе")
    })
    @GetMapping("/getUsers")
    public ResponseEntity<List<UserEntity>> findUsers(
            @RequestParam @Parameter(description = "значение фильтра")  @Valid String value,
                                    @RequestParam ("filterType") @Valid @Parameter(description = "тип фильтра")final FILTER_TYPE filterType,
                                    @RequestParam @Valid @Parameter(description = "количество записей на странице") final int size,
                                    @RequestParam @Valid @Parameter(description = "порядковый номер страницы")final int page){

        final UserSearchDto userSearchDto = new UserSearchDto(value, filterType, size, page);

        return ResponseEntity.ok(userService.getUsers(userSearchDto));
    }
}

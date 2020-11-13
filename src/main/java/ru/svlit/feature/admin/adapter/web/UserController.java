package ru.svlit.feature.admin.adapter.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.svlit.architecture.annotation.WebAdapter;
import ru.svlit.core.util.NavigationContent;
import ru.svlit.core.util.UnifiedModelAndView;
import ru.svlit.feature.authentication.application.port.in.FindUserByIdUseCase;
import ru.svlit.feature.authentication.application.port.in.GetAllUsersUseCase;
import ru.svlit.feature.authentication.application.port.in.RemoveUserByIdUseCase;
import ru.svlit.feature.authentication.application.port.in.UpdateUserUseCase;
import ru.svlit.feature.authentication.application.port.in.UpdateUserUseCase.UpdateUserCommand;
import ru.svlit.feature.authentication.domain.Role;
import ru.svlit.feature.authentication.domain.User;
import ru.svlit.feature.home.application.port.in.GetNavigationContentUseCase;
import ru.svlit.feature.home.application.port.in.GetNavigationContentUseCase.GetNavigationContentCommand;
import ru.svlit.feature.sweater.application.port.in.SubmitMessageUseCase.UserNotFoundException;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;

@WebAdapter
@RequestMapping("/user")
@RequiredArgsConstructor
class UserController {

    private final GetAllUsersUseCase getAllUsersUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final RemoveUserByIdUseCase removeUserByIdUseCase;
    private final GetNavigationContentUseCase getNavigationContentUseCase;

    @GetMapping
    public UnifiedModelAndView getUsers() {
        final Iterable<User> users = getAllUsersUseCase.getAllUsers();
        final NavigationContent navigationContent = getNavigationContentUseCase.perform(
                new GetNavigationContentCommand(true)
        );

        final UnifiedModelAndView modelAndView = new UnifiedModelAndView("users", navigationContent);
        modelAndView.addObject("users", users);
        modelAndView.addObject("roles", Role.values());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public UnifiedModelAndView getUserDetails(@PathVariable final String id) throws UserNotFoundException {
        final User user = findUserByIdUseCase.findByUd(id).orElseThrow(UserNotFoundException::new);
        final NavigationContent navigationContent = getNavigationContentUseCase.perform(
                new GetNavigationContentCommand(true)
        );

        final UnifiedModelAndView modelAndView = new UnifiedModelAndView("user", navigationContent);
        modelAndView.addObject("user", user);
        modelAndView.addObject("roles", Role.values());
        return modelAndView;
    }

    @PostMapping
    public String saveUser(@RequestParam("id") String id,
                           @RequestParam("username") String updatedUsername,
                           @RequestParam Map<String, String> form) throws UserNotFoundException {
        final User user = findUserByIdUseCase.findByUd(id).orElseThrow(UserNotFoundException::new);

        user.getRoles().clear();

        final Set<String> roleStrings = stream(Role.values()).map(Enum::name).collect(toSet());
        final Set<Role> roles = new HashSet<>();
        for (String key : form.keySet()) {
            if (roleStrings.contains(key)) {
                roles.add(Role.valueOf(key));
            }
        }

        updateUserUseCase.updateUser(new UpdateUserCommand(
                user.getId(),
                updatedUsername,
                roles
        ));

        return "redirect:/user";
    }

    @PostMapping("/remove/{id}")
    public String remove(@PathVariable final String id) throws UserNotFoundException {
        final User user = findUserByIdUseCase.findByUd(id).orElseThrow(UserNotFoundException::new);
        removeUserByIdUseCase.removeById(user.getId());
        return "redirect:/user";
    }
}

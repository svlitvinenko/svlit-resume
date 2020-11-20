package ru.svlit.feature.admin.adapter.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.svlit.architecture.annotation.WebAdapter;
import ru.svlit.core.global.configuration.security.Role;
import ru.svlit.core.util.NavigationContent;
import ru.svlit.core.util.Redirect;
import ru.svlit.core.util.UnifiedModelAndView;
import ru.svlit.core.user.application.port.in.FindUserByIdUseCase;
import ru.svlit.core.user.application.port.in.GetAllUsersUseCase;
import ru.svlit.core.user.application.port.in.RemoveUserByIdUseCase;
import ru.svlit.core.user.application.port.in.UpdateUserUseCase;
import ru.svlit.core.user.application.port.in.UpdateUserUseCase.UpdateUserCommand;
import ru.svlit.feature.authentication.domain.User;
import ru.svlit.feature.home.application.port.in.GetNavigationContentUseCase;
import ru.svlit.feature.home.application.port.in.GetNavigationContentUseCase.GetNavigationContentCommand;
import ru.svlit.feature.wall.application.port.in.SubmitMessageUseCase.UserNotFoundException;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;
import static ru.svlit.feature.admin.configuration.AdminPanelConfigurationConstants.*;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping(PATH_ADMIN_PANEL)
class AdminPanelController {

    private static final String MAPPING_GET_USER_DETAILS = "/{id}";
    private static final String MAPPING_REMOVE_USER_BY_ID = "/remove/{id}";
    private static final String REQUEST_PARAM_USER_ID = "id";
    private static final String REQUEST_PARAM_USER_NAME = "username";
    private static final String REQUEST_PARAM_EMAIL = "email";

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

        final UnifiedModelAndView modelAndView = new UnifiedModelAndView(SEGMENT_ADMIN_PANEL, navigationContent);
        modelAndView.addObject("users", users);
        modelAndView.addObject("roles", Role.values());
        return modelAndView;
    }

    @GetMapping(MAPPING_GET_USER_DETAILS)
    public UnifiedModelAndView getUserDetails(@PathVariable final String id) throws UserNotFoundException {
        final User user = findUserByIdUseCase.findByUd(id).orElseThrow(UserNotFoundException::new);
        final NavigationContent navigationContent = getNavigationContentUseCase.perform(
                new GetNavigationContentCommand(true)
        );

        final UnifiedModelAndView modelAndView = new UnifiedModelAndView(SEGMENT_ADMIN_PANEL_USER_DETAILS, navigationContent);
        modelAndView.addObject("user", user);
        modelAndView.addObject("roles", Role.values());
        return modelAndView;
    }

    @PostMapping
    public String saveUser(@RequestParam(REQUEST_PARAM_USER_ID) String id,
                           @RequestParam(REQUEST_PARAM_USER_NAME) String updatedUsername,
                           @RequestParam(REQUEST_PARAM_EMAIL) String updatedEmail,
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
                updatedEmail,
                roles,
                user.getSelectedLocale()
        ));

        return Redirect.to(PATH_ADMIN_PANEL);
    }

    @PostMapping(MAPPING_REMOVE_USER_BY_ID)
    public String remove(@PathVariable final String id) throws UserNotFoundException {
        final User user = findUserByIdUseCase.findByUd(id).orElseThrow(UserNotFoundException::new);
        removeUserByIdUseCase.removeById(user.getId());
        return Redirect.to(PATH_ADMIN_PANEL);
    }
}

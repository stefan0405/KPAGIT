(function () {
    'use strict';
    /* global angular*/
    angular
            .module('passwordToggle', [])
            .directive('togglePassword', togglePassword);

    function togglePassword() {
        return {
            restrict: 'A',
            link: link
        };

        function link(scope, element, attrs) {
            var showIcon = "glyphicon glyphicon-eye-open";
            var hideIcon = "glyphicon glyphicon-eye-close";

            if (!element.hasClass('form-control')) {
                element.addClass('form-control');
            }
            var aTag = document.createElement('a');
            aTag.className = "input-group-addon";
            aTag.setAttribute('role', 'button');
            aTag.href = "javascript:void(0)";
            aTag.addEventListener('click', toggle);
            var iTag = document.createElement('i');
            iTag.className = "glyphicon glyphicon-eye-open";
            aTag.appendChild(iTag);
            element[0].parentNode.insertBefore(aTag, element.nextSibling);

            function toggle() {
                var type = element[0].type === 'text' ? 'password' : 'text';
                element.attr('type', type);
                iTag.className = iTag.className === hideIcon ? showIcon : hideIcon;
            }

        }
    }
})();
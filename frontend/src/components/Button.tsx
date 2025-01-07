import React from 'react';

interface ButtonProps {
    onClick: () => void;
    type?: 'button' | 'submit' | 'reset';
    children: React.ReactNode;
    disabled?: boolean;
    className?: string;
    variant?: "create" | "search" | "reset";
}

const Button: React.FC<ButtonProps> = ({ type, onClick, children, variant }) => {
    const buttonClass = `button ${variant || ""}`;
    return (
        <button className={buttonClass} type={type} onClick={onClick}>
            {children}
        </button>
    );
};

export default Button;
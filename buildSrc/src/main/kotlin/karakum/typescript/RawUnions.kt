package karakum.typescript

internal val RAW_UNIONS = mapOf(
    "TriviaSyntaxKind" to "SyntaxKind.SingleLineCommentTrivia | SyntaxKind.MultiLineCommentTrivia | SyntaxKind.NewLineTrivia | SyntaxKind.WhitespaceTrivia | SyntaxKind.ShebangTrivia | SyntaxKind.ConflictMarkerTrivia",
    "LiteralSyntaxKind" to "SyntaxKind.NumericLiteral | SyntaxKind.BigIntLiteral | SyntaxKind.StringLiteral | SyntaxKind.JsxText | SyntaxKind.JsxTextAllWhiteSpaces | SyntaxKind.RegularExpressionLiteral | SyntaxKind.NoSubstitutionTemplateLiteral",
    "PseudoLiteralSyntaxKind" to "SyntaxKind.TemplateHead | SyntaxKind.TemplateMiddle | SyntaxKind.TemplateTail",
    "PunctuationSyntaxKind" to "SyntaxKind.OpenBraceToken | SyntaxKind.CloseBraceToken | SyntaxKind.OpenParenToken | SyntaxKind.CloseParenToken | SyntaxKind.OpenBracketToken | SyntaxKind.CloseBracketToken | SyntaxKind.DotToken | SyntaxKind.DotDotDotToken | SyntaxKind.SemicolonToken | SyntaxKind.CommaToken | SyntaxKind.QuestionDotToken | SyntaxKind.LessThanToken | SyntaxKind.LessThanSlashToken | SyntaxKind.GreaterThanToken | SyntaxKind.LessThanEqualsToken | SyntaxKind.GreaterThanEqualsToken | SyntaxKind.EqualsEqualsToken | SyntaxKind.ExclamationEqualsToken | SyntaxKind.EqualsEqualsEqualsToken | SyntaxKind.ExclamationEqualsEqualsToken | SyntaxKind.EqualsGreaterThanToken | SyntaxKind.PlusToken | SyntaxKind.MinusToken | SyntaxKind.AsteriskToken | SyntaxKind.AsteriskAsteriskToken | SyntaxKind.SlashToken | SyntaxKind.PercentToken | SyntaxKind.PlusPlusToken | SyntaxKind.MinusMinusToken | SyntaxKind.LessThanLessThanToken | SyntaxKind.GreaterThanGreaterThanToken | SyntaxKind.GreaterThanGreaterThanGreaterThanToken | SyntaxKind.AmpersandToken | SyntaxKind.BarToken | SyntaxKind.CaretToken | SyntaxKind.ExclamationToken | SyntaxKind.TildeToken | SyntaxKind.AmpersandAmpersandToken | SyntaxKind.BarBarToken | SyntaxKind.QuestionQuestionToken | SyntaxKind.QuestionToken | SyntaxKind.ColonToken | SyntaxKind.AtToken | SyntaxKind.BacktickToken | SyntaxKind.HashToken | SyntaxKind.EqualsToken | SyntaxKind.PlusEqualsToken | SyntaxKind.MinusEqualsToken | SyntaxKind.AsteriskEqualsToken | SyntaxKind.AsteriskAsteriskEqualsToken | SyntaxKind.SlashEqualsToken | SyntaxKind.PercentEqualsToken | SyntaxKind.LessThanLessThanEqualsToken | SyntaxKind.GreaterThanGreaterThanEqualsToken | SyntaxKind.GreaterThanGreaterThanGreaterThanEqualsToken | SyntaxKind.AmpersandEqualsToken | SyntaxKind.BarEqualsToken | SyntaxKind.CaretEqualsToken",
    "KeywordSyntaxKind" to "SyntaxKind.AbstractKeyword | SyntaxKind.AnyKeyword | SyntaxKind.AsKeyword | SyntaxKind.AssertsKeyword | SyntaxKind.AssertKeyword | SyntaxKind.AsyncKeyword | SyntaxKind.AwaitKeyword | SyntaxKind.BigIntKeyword | SyntaxKind.BooleanKeyword | SyntaxKind.BreakKeyword | SyntaxKind.CaseKeyword | SyntaxKind.CatchKeyword | SyntaxKind.ClassKeyword | SyntaxKind.ConstKeyword | SyntaxKind.ConstructorKeyword | SyntaxKind.ContinueKeyword | SyntaxKind.DebuggerKeyword | SyntaxKind.DeclareKeyword | SyntaxKind.DefaultKeyword | SyntaxKind.DeleteKeyword | SyntaxKind.DoKeyword | SyntaxKind.ElseKeyword | SyntaxKind.EnumKeyword | SyntaxKind.ExportKeyword | SyntaxKind.ExtendsKeyword | SyntaxKind.FalseKeyword | SyntaxKind.FinallyKeyword | SyntaxKind.ForKeyword | SyntaxKind.FromKeyword | SyntaxKind.FunctionKeyword | SyntaxKind.GetKeyword | SyntaxKind.GlobalKeyword | SyntaxKind.IfKeyword | SyntaxKind.ImplementsKeyword | SyntaxKind.ImportKeyword | SyntaxKind.InferKeyword | SyntaxKind.InKeyword | SyntaxKind.InstanceOfKeyword | SyntaxKind.InterfaceKeyword | SyntaxKind.IntrinsicKeyword | SyntaxKind.IsKeyword | SyntaxKind.KeyOfKeyword | SyntaxKind.LetKeyword | SyntaxKind.ModuleKeyword | SyntaxKind.NamespaceKeyword | SyntaxKind.NeverKeyword | SyntaxKind.NewKeyword | SyntaxKind.NullKeyword | SyntaxKind.NumberKeyword | SyntaxKind.ObjectKeyword | SyntaxKind.OfKeyword | SyntaxKind.PackageKeyword | SyntaxKind.PrivateKeyword | SyntaxKind.ProtectedKeyword | SyntaxKind.PublicKeyword | SyntaxKind.ReadonlyKeyword | SyntaxKind.OverrideKeyword | SyntaxKind.RequireKeyword | SyntaxKind.ReturnKeyword | SyntaxKind.SetKeyword | SyntaxKind.StaticKeyword | SyntaxKind.StringKeyword | SyntaxKind.SuperKeyword | SyntaxKind.SwitchKeyword | SyntaxKind.SymbolKeyword | SyntaxKind.ThisKeyword | SyntaxKind.ThrowKeyword | SyntaxKind.TrueKeyword | SyntaxKind.TryKeyword | SyntaxKind.TypeKeyword | SyntaxKind.TypeOfKeyword | SyntaxKind.UndefinedKeyword | SyntaxKind.UniqueKeyword | SyntaxKind.UnknownKeyword | SyntaxKind.VarKeyword | SyntaxKind.VoidKeyword | SyntaxKind.WhileKeyword | SyntaxKind.WithKeyword | SyntaxKind.YieldKeyword",
    "ModifierSyntaxKind" to "SyntaxKind.AbstractKeyword | SyntaxKind.AsyncKeyword | SyntaxKind.ConstKeyword | SyntaxKind.DeclareKeyword | SyntaxKind.DefaultKeyword | SyntaxKind.ExportKeyword | SyntaxKind.PrivateKeyword | SyntaxKind.ProtectedKeyword | SyntaxKind.PublicKeyword | SyntaxKind.ReadonlyKeyword | SyntaxKind.OverrideKeyword | SyntaxKind.StaticKeyword",
    "KeywordTypeSyntaxKind" to "SyntaxKind.AnyKeyword | SyntaxKind.BigIntKeyword | SyntaxKind.BooleanKeyword | SyntaxKind.IntrinsicKeyword | SyntaxKind.NeverKeyword | SyntaxKind.NumberKeyword | SyntaxKind.ObjectKeyword | SyntaxKind.StringKeyword | SyntaxKind.SymbolKeyword | SyntaxKind.UndefinedKeyword | SyntaxKind.UnknownKeyword | SyntaxKind.VoidKeyword",
    "TokenSyntaxKind" to "SyntaxKind.Unknown | SyntaxKind.EndOfFileToken | TriviaSyntaxKind | LiteralSyntaxKind | PseudoLiteralSyntaxKind | PunctuationSyntaxKind | SyntaxKind.Identifier | KeywordSyntaxKind",
    "JsxTokenSyntaxKind" to "SyntaxKind.LessThanSlashToken | SyntaxKind.EndOfFileToken | SyntaxKind.ConflictMarkerTrivia | SyntaxKind.JsxText | SyntaxKind.JsxTextAllWhiteSpaces | SyntaxKind.OpenBraceToken | SyntaxKind.LessThanToken",
    "JSDocSyntaxKind" to "SyntaxKind.EndOfFileToken | SyntaxKind.WhitespaceTrivia | SyntaxKind.AtToken | SyntaxKind.NewLineTrivia | SyntaxKind.AsteriskToken | SyntaxKind.OpenBraceToken | SyntaxKind.CloseBraceToken | SyntaxKind.LessThanToken | SyntaxKind.GreaterThanToken | SyntaxKind.OpenBracketToken | SyntaxKind.CloseBracketToken | SyntaxKind.EqualsToken | SyntaxKind.CommaToken | SyntaxKind.DotToken | SyntaxKind.Identifier | SyntaxKind.BacktickToken | SyntaxKind.HashToken | SyntaxKind.Unknown | KeywordSyntaxKind",
    "HasJSDoc" to "ParameterDeclaration | CallSignatureDeclaration | ClassStaticBlockDeclaration | ConstructSignatureDeclaration | MethodSignature | PropertySignature | ArrowFunction | ParenthesizedExpression | SpreadAssignment | ShorthandPropertyAssignment | PropertyAssignment | FunctionExpression | EmptyStatement | DebuggerStatement | Block | VariableStatement | ExpressionStatement | IfStatement | DoStatement | WhileStatement | ForStatement | ForInStatement | ForOfStatement | BreakStatement | ContinueStatement | ReturnStatement | WithStatement | SwitchStatement | LabeledStatement | ThrowStatement | TryStatement | FunctionDeclaration | ConstructorDeclaration | MethodDeclaration | VariableDeclaration | PropertyDeclaration | AccessorDeclaration | ClassLikeDeclaration | InterfaceDeclaration | TypeAliasDeclaration | EnumMember | EnumDeclaration | ModuleDeclaration | ImportEqualsDeclaration | ImportDeclaration | NamespaceExportDeclaration | ExportAssignment | IndexSignatureDeclaration | FunctionTypeNode | ConstructorTypeNode | JSDocFunctionType | ExportDeclaration | NamedTupleMember | EndOfFileToken",
    "HasType" to "SignatureDeclaration | VariableDeclaration | ParameterDeclaration | PropertySignature | PropertyDeclaration | TypePredicateNode | ParenthesizedTypeNode | TypeOperatorNode | MappedTypeNode | AssertionExpression | TypeAliasDeclaration | JSDocTypeExpression | JSDocNonNullableType | JSDocNullableType | JSDocOptionalType | JSDocVariadicType",
    "HasTypeArguments" to "CallExpression | NewExpression | TaggedTemplateExpression | JsxOpeningElement | JsxSelfClosingElement",
    "HasInitializer" to "HasExpressionInitializer | ForStatement | ForInStatement | ForOfStatement | JsxAttribute",
    "HasExpressionInitializer" to "VariableDeclaration | ParameterDeclaration | BindingElement | PropertySignature | PropertyDeclaration | PropertyAssignment | EnumMember",
    "Modifier" to "AbstractKeyword | AsyncKeyword | ConstKeyword | DeclareKeyword | DefaultKeyword | ExportKeyword | PrivateKeyword | ProtectedKeyword | PublicKeyword | OverrideKeyword | ReadonlyKeyword | StaticKeyword",
    "AccessibilityModifier" to "PublicKeyword | PrivateKeyword | ProtectedKeyword",
    "ParameterPropertyModifier" to "AccessibilityModifier | ReadonlyKeyword",
    "ClassMemberModifier" to "AccessibilityModifier | ReadonlyKeyword | StaticKeyword",
    "EntityName" to "Identifier | QualifiedName",
    "PropertyName" to "Identifier | StringLiteral | NumericLiteral | ComputedPropertyName | PrivateIdentifier",
    "MemberName" to "Identifier | PrivateIdentifier",
    "DeclarationName" to "Identifier | PrivateIdentifier | StringLiteralLike | NumericLiteral | ComputedPropertyName | ElementAccessExpression | BindingPattern | EntityNameExpression",
    "SignatureDeclaration" to "CallSignatureDeclaration | ConstructSignatureDeclaration | MethodSignature | IndexSignatureDeclaration | FunctionTypeNode | ConstructorTypeNode | JSDocFunctionType | FunctionDeclaration | MethodDeclaration | ConstructorDeclaration | AccessorDeclaration | FunctionExpression | ArrowFunction",
    "BindingName" to "Identifier | BindingPattern",
    "ObjectLiteralElementLike" to "PropertyAssignment | ShorthandPropertyAssignment | SpreadAssignment | MethodDeclaration | AccessorDeclaration",
    "VariableLikeDeclaration" to "VariableDeclaration | ParameterDeclaration | BindingElement | PropertyDeclaration | PropertyAssignment | PropertySignature | JsxAttribute | ShorthandPropertyAssignment | EnumMember | JSDocPropertyTag | JSDocParameterTag",
    "BindingPattern" to "ObjectBindingPattern | ArrayBindingPattern",
    "ArrayBindingElement" to "BindingElement | OmittedExpression",
    "FunctionLikeDeclaration" to "FunctionDeclaration | MethodDeclaration | GetAccessorDeclaration | SetAccessorDeclaration | ConstructorDeclaration | FunctionExpression | ArrowFunction",
    "AccessorDeclaration" to "GetAccessorDeclaration | SetAccessorDeclaration",
    "FunctionOrConstructorTypeNode" to "FunctionTypeNode | ConstructorTypeNode",
    "TypeReferenceType" to "TypeReferenceNode | ExpressionWithTypeArguments",
    "UnionOrIntersectionTypeNode" to "UnionTypeNode | IntersectionTypeNode",
    "StringLiteralLike" to "StringLiteral | NoSubstitutionTemplateLiteral",
    "PropertyNameLiteral" to "Identifier | StringLiteralLike | NumericLiteral",
    "PrefixUnaryOperator" to "SyntaxKind.PlusPlusToken | SyntaxKind.MinusMinusToken | SyntaxKind.PlusToken | SyntaxKind.MinusToken | SyntaxKind.TildeToken | SyntaxKind.ExclamationToken",
    "PostfixUnaryOperator" to "SyntaxKind.PlusPlusToken | SyntaxKind.MinusMinusToken",
    "BooleanLiteral" to "TrueLiteral | FalseLiteral",
    "MultiplicativeOperator" to "SyntaxKind.AsteriskToken | SyntaxKind.SlashToken | SyntaxKind.PercentToken",
    "MultiplicativeOperatorOrHigher" to "ExponentiationOperator | MultiplicativeOperator",
    "AdditiveOperator" to "SyntaxKind.PlusToken | SyntaxKind.MinusToken",
    "AdditiveOperatorOrHigher" to "MultiplicativeOperatorOrHigher | AdditiveOperator",
    "ShiftOperator" to "SyntaxKind.LessThanLessThanToken | SyntaxKind.GreaterThanGreaterThanToken | SyntaxKind.GreaterThanGreaterThanGreaterThanToken",
    "ShiftOperatorOrHigher" to "AdditiveOperatorOrHigher | ShiftOperator",
    "RelationalOperator" to "SyntaxKind.LessThanToken | SyntaxKind.LessThanEqualsToken | SyntaxKind.GreaterThanToken | SyntaxKind.GreaterThanEqualsToken | SyntaxKind.InstanceOfKeyword | SyntaxKind.InKeyword",
    "RelationalOperatorOrHigher" to "ShiftOperatorOrHigher | RelationalOperator",
    "EqualityOperator" to "SyntaxKind.EqualsEqualsToken | SyntaxKind.EqualsEqualsEqualsToken | SyntaxKind.ExclamationEqualsEqualsToken | SyntaxKind.ExclamationEqualsToken",
    "EqualityOperatorOrHigher" to "RelationalOperatorOrHigher | EqualityOperator",
    "BitwiseOperator" to "SyntaxKind.AmpersandToken | SyntaxKind.BarToken | SyntaxKind.CaretToken",
    "BitwiseOperatorOrHigher" to "EqualityOperatorOrHigher | BitwiseOperator",
    "LogicalOperator" to "SyntaxKind.AmpersandAmpersandToken | SyntaxKind.BarBarToken",
    "LogicalOperatorOrHigher" to "BitwiseOperatorOrHigher | LogicalOperator",
    "CompoundAssignmentOperator" to "SyntaxKind.PlusEqualsToken | SyntaxKind.MinusEqualsToken | SyntaxKind.AsteriskAsteriskEqualsToken | SyntaxKind.AsteriskEqualsToken | SyntaxKind.SlashEqualsToken | SyntaxKind.PercentEqualsToken | SyntaxKind.AmpersandEqualsToken | SyntaxKind.BarEqualsToken | SyntaxKind.CaretEqualsToken | SyntaxKind.LessThanLessThanEqualsToken | SyntaxKind.GreaterThanGreaterThanGreaterThanEqualsToken | SyntaxKind.GreaterThanGreaterThanEqualsToken | SyntaxKind.BarBarEqualsToken | SyntaxKind.AmpersandAmpersandEqualsToken | SyntaxKind.QuestionQuestionEqualsToken",
    "AssignmentOperator" to "SyntaxKind.EqualsToken | CompoundAssignmentOperator",
    "AssignmentOperatorOrHigher" to "SyntaxKind.QuestionQuestionToken | LogicalOperatorOrHigher | AssignmentOperator",
    "BinaryOperator" to "AssignmentOperatorOrHigher | SyntaxKind.CommaToken",
    "LogicalOrCoalescingAssignmentOperator" to "SyntaxKind.AmpersandAmpersandEqualsToken | SyntaxKind.BarBarEqualsToken | SyntaxKind.QuestionQuestionEqualsToken",
    "DestructuringAssignment" to "ObjectDestructuringAssignment | ArrayDestructuringAssignment",
    "BindingOrAssignmentElement" to "VariableDeclaration | ParameterDeclaration | ObjectBindingOrAssignmentElement | ArrayBindingOrAssignmentElement",
    "ObjectBindingOrAssignmentElement" to "BindingElement | PropertyAssignment | ShorthandPropertyAssignment | SpreadAssignment",
    "BindingOrAssignmentElementRestIndicator" to "DotDotDotToken | SpreadElement | SpreadAssignment",
    "BindingOrAssignmentElementTarget" to "BindingOrAssignmentPattern | Identifier | PropertyAccessExpression | ElementAccessExpression | OmittedExpression",
    "ObjectBindingOrAssignmentPattern" to "ObjectBindingPattern | ObjectLiteralExpression",
    "ArrayBindingOrAssignmentPattern" to "ArrayBindingPattern | ArrayLiteralExpression",
    "AssignmentPattern" to "ObjectLiteralExpression | ArrayLiteralExpression",
    "BindingOrAssignmentPattern" to "ObjectBindingOrAssignmentPattern | ArrayBindingOrAssignmentPattern",
    "ConciseBody" to "FunctionBody | Expression",
    "LiteralToken" to "NumericLiteral | BigIntLiteral | StringLiteral | JsxText | RegularExpressionLiteral | NoSubstitutionTemplateLiteral",
    "PseudoLiteralToken" to "TemplateHead | TemplateMiddle | TemplateTail",
    "TemplateLiteralToken" to "NoSubstitutionTemplateLiteral | PseudoLiteralToken",
    "TemplateLiteral" to "TemplateExpression | NoSubstitutionTemplateLiteral",
    "EntityNameExpression" to "Identifier | PropertyAccessEntityNameExpression",
    "EntityNameOrEntityNameExpression" to "EntityName | EntityNameExpression",
    "AccessExpression" to "PropertyAccessExpression | ElementAccessExpression",
    "SuperProperty" to "SuperPropertyAccessExpression | SuperElementAccessExpression",
    "OptionalChain" to "PropertyAccessChain | ElementAccessChain | CallChain | NonNullChain",
    "CallLikeExpression" to "CallExpression | NewExpression | TaggedTemplateExpression | Decorator | JsxOpeningLikeElement",
    "AssertionExpression" to "TypeAssertion | AsExpression",
    "JsxOpeningLikeElement" to "JsxSelfClosingElement | JsxOpeningElement",
    "JsxAttributeLike" to "JsxAttribute | JsxSpreadAttribute",
    "JsxTagNameExpression" to "Identifier | ThisExpression | JsxTagNamePropertyAccess",
    "JsxChild" to "JsxText | JsxExpression | JsxElement | JsxSelfClosingElement | JsxFragment",
    "BlockLike" to "SourceFile | Block | ModuleBlock | CaseOrDefaultClause",
    "ForInitializer" to "VariableDeclarationList | Expression",
    "ForInOrOfStatement" to "ForInStatement | ForOfStatement",
    "BreakOrContinueStatement" to "BreakStatement | ContinueStatement",
    "CaseOrDefaultClause" to "CaseClause | DefaultClause",
    "ObjectTypeDeclaration" to "ClassLikeDeclaration | InterfaceDeclaration | TypeLiteralNode",
    "DeclarationWithTypeParameters" to "DeclarationWithTypeParameterChildren | JSDocTypedefTag | JSDocCallbackTag | JSDocSignature",
    "DeclarationWithTypeParameterChildren" to "SignatureDeclaration | ClassLikeDeclaration | InterfaceDeclaration | TypeAliasDeclaration | JSDocTemplateTag",
    "ClassLikeDeclaration" to "ClassDeclaration | ClassExpression",
    "ModuleName" to "Identifier | StringLiteral",
    "ModuleBody" to "NamespaceBody | JSDocNamespaceBody",
    "NamespaceBody" to "ModuleBlock | NamespaceDeclaration",
    "JSDocNamespaceBody" to "Identifier | JSDocNamespaceDeclaration",
    "ModuleReference" to "EntityName | ExternalModuleReference",
    "NamedImportBindings" to "NamespaceImport | NamedImports",
    "NamedExportBindings" to "NamespaceExport | NamedExports",
    "AssertionKey" to "Identifier | StringLiteral",
    "NamedImportsOrExports" to "NamedImports | NamedExports",
    "ImportOrExportSpecifier" to "ImportSpecifier | ExportSpecifier",
    "TypeOnlyCompatibleAliasDeclaration" to "ImportClause | ImportEqualsDeclaration | NamespaceImport | ImportOrExportSpecifier",
    "CommentKind" to "SyntaxKind.SingleLineCommentTrivia | SyntaxKind.MultiLineCommentTrivia",
    "JSDocTypeReferencingNode" to "JSDocVariadicType | JSDocOptionalType | JSDocNullableType | JSDocNonNullableType",
    "JSDocComment" to "JSDocText | JSDocLink | JSDocLinkCode | JSDocLinkPlain",
    "FlowNode" to "FlowStart | FlowLabel | FlowAssignment | FlowCall | FlowCondition | FlowSwitchClause | FlowArrayMutation | FlowCall | FlowReduceLabel",
    "FlowType" to "Type | IncompleteType",
    "UnparsedSourceText" to "UnparsedPrepend | UnparsedTextLike",
    "UnparsedNode" to "UnparsedPrologue | UnparsedSourceText | UnparsedSyntheticReference",
    "JsonObjectExpression" to "ObjectLiteralExpression | ArrayLiteralExpression | JsonMinusNumericLiteral | NumericLiteral | StringLiteral | BooleanLiteral | NullLiteral",
    "TypePredicate" to "ThisTypePredicate | IdentifierTypePredicate | AssertsThisTypePredicate | AssertsIdentifierTypePredicate",
    "DestructuringPattern" to "BindingPattern | ObjectLiteralExpression | ArrayLiteralExpression",
    "BaseType" to "ObjectType | IntersectionType | TypeVariable",
    "StructuredType" to "ObjectType | UnionType | IntersectionType",
    "TypeVariable" to "TypeParameter | IndexedAccessType",
    "EmitHelper" to "ScopedEmitHelper | UnscopedEmitHelper",
    "SignatureHelpTriggerReason" to "SignatureHelpInvokedReason | SignatureHelpCharacterTypedReason | SignatureHelpRetriggeredReason",
    "RenameInfo" to "RenameInfoSuccess | RenameInfoFailure",
    "CompletionEntryData" to "CompletionEntryDataUnresolved | CompletionEntryDataResolved",
    "DeclarationStatement.name" to "Identifier | StringLiteral | NumericLiteral",
    "TypeParameterDeclaration.parent" to "DeclarationWithTypeParameterChildren | InferTypeNode",
    "VariableDeclaration.parent" to "VariableDeclarationList | CatchClause",
    "VariableDeclarationList.parent" to "VariableStatement | ForStatement | ForOfStatement | ForInStatement",
    "ObjectBindingPattern.parent" to "VariableDeclaration | ParameterDeclaration | BindingElement",
    "ArrayBindingPattern.parent" to "VariableDeclaration | ParameterDeclaration | BindingElement",
    "FunctionLikeDeclarationBase.body" to "Block | Expression",
    "MethodDeclaration.parent" to "ClassLikeDeclaration | ObjectLiteralExpression",
    "GetAccessorDeclaration.parent" to "ClassLikeDeclaration | ObjectLiteralExpression | TypeLiteralNode | InterfaceDeclaration",
    "SetAccessorDeclaration.parent" to "ClassLikeDeclaration | ObjectLiteralExpression | TypeLiteralNode | InterfaceDeclaration",
    "ClassStaticBlockDeclaration.parent" to "ClassDeclaration | ClassExpression",
    "FunctionOrConstructorTypeNodeBase.kind" to "SyntaxKind.FunctionType | SyntaxKind.ConstructorType",
    "TypePredicateNode.parent" to "SignatureDeclaration | JSDocTypeExpression",
    "TypePredicateNode.parameterName" to "Identifier | ThisTypeNode",
    "TypeOperatorNode.operator" to "SyntaxKind.KeyOfKeyword | SyntaxKind.UniqueKeyword | SyntaxKind.ReadonlyKeyword",
    "MappedTypeNode.readonlyToken" to "ReadonlyToken | PlusToken | MinusToken",
    "MappedTypeNode.questionToken" to "QuestionToken | PlusToken | MinusToken",
    "LiteralTypeNode.literal" to "NullLiteral | BooleanLiteral | LiteralExpression | PrefixUnaryExpression",
    "TemplateLiteralTypeSpan.literal" to "TemplateMiddle | TemplateTail",
    "SyntheticExpression.tupleNameSource" to "ParameterDeclaration | NamedTupleMember",
    "TemplateHead.parent" to "TemplateExpression | TemplateLiteralTypeNode",
    "TemplateMiddle.parent" to "TemplateSpan | TemplateLiteralTypeSpan",
    "TemplateTail.parent" to "TemplateSpan | TemplateLiteralTypeSpan",
    "TemplateSpan.literal" to "TemplateMiddle | TemplateTail",
    "SpreadElement.parent" to "ArrayLiteralExpression | CallExpression | NewExpression",
    "ExpressionWithTypeArguments.parent" to "HeritageClause | JSDocAugmentsTag | JSDocImplementsTag",
    "MetaProperty.keywordToken" to "SyntaxKind.NewKeyword | SyntaxKind.ImportKeyword",
    "JsxAttribute.initializer" to "StringLiteral | JsxExpression",
    "JsxExpression.parent" to "JsxElement | JsxFragment | JsxAttributeLike",
    "JsxText.parent" to "JsxElement | JsxFragment",
    "ClassLikeDeclarationBase.kind" to "SyntaxKind.ClassDeclaration | SyntaxKind.ClassExpression",
    "HeritageClause.parent" to "InterfaceDeclaration | ClassLikeDeclaration",
    "HeritageClause.token" to "SyntaxKind.ExtendsKeyword | SyntaxKind.ImplementsKeyword",
    "ModuleDeclaration.parent" to "ModuleBody | SourceFile",
    "ModuleDeclaration.body" to "ModuleBody | JSDocNamespaceDeclaration",
    "ImportEqualsDeclaration.parent" to "SourceFile | ModuleBlock",
    "ImportDeclaration.parent" to "SourceFile | ModuleBlock",
    "AssertClause.parent" to "ImportDeclaration | ExportDeclaration",
    "ExportDeclaration.parent" to "SourceFile | ModuleBlock",
    "JSDocNameReference.name" to "EntityName | JSDocMemberName",
    "JSDocMemberName.left" to "EntityName | JSDocMemberName",
    "JSDocTag.parent" to "JSDoc | JSDocTypeLiteral",
    "JSDocLink.name" to "EntityName | JSDocMemberName",
    "JSDocLinkCode.name" to "EntityName | JSDocMemberName",
    "JSDocLinkPlain.name" to "EntityName | JSDocMemberName",
    "JSDocTypedefTag.fullName" to "JSDocNamespaceDeclaration | Identifier",
    "JSDocTypedefTag.typeExpression" to "JSDocTypeExpression | JSDocTypeLiteral",
    "JSDocCallbackTag.fullName" to "JSDocNamespaceDeclaration | Identifier",
    "FlowStart.node" to "FunctionExpression | ArrowFunction | MethodDeclaration | GetAccessorDeclaration | SetAccessorDeclaration",
    "FlowAssignment.node" to "Expression | VariableDeclaration | BindingElement",
    "FlowArrayMutation.node" to "CallExpression | BinaryExpression",
    "UnparsedTextLike.kind" to "SyntaxKind.UnparsedText | SyntaxKind.UnparsedInternalText",
    "TypeReference.node" to "TypeReferenceNode | ArrayTypeNode | TupleTypeNode",
    "IndexType.type" to "InstantiableType | UnionOrIntersectionType",
    "Signature.declaration" to "SignatureDeclaration | JSDocSignature",
)